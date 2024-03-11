package com.ruoyi.server.service.impl;

import com.ruoyi.server.async.AsyncManager;
import com.ruoyi.server.common.EmailTemplate;
import com.ruoyi.server.common.PushEmail;
import com.ruoyi.server.common.RconUtil;
import com.ruoyi.server.domain.WhitelistInfo;
import com.ruoyi.server.mapper.ServerInfoMapper;
import com.ruoyi.server.mapper.WhitelistInfoMapper;
import com.ruoyi.server.service.IWhitelistInfoService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * 白名单Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-26
 */
@Service
public class WhitelistInfoServiceImpl implements IWhitelistInfoService {
    private static final Log log = LogFactory.getLog(WhitelistInfoServiceImpl.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 异步执行器
    private final AsyncManager asyncManager = AsyncManager.getInstance();
    @Autowired
    private WhitelistInfoMapper whitelistInfoMapper;
    @Autowired
    private ServerInfoMapper serverInfoMapper;
    @Autowired
    private PushEmail pushEmail;

    /**
     * 查询白名单
     *
     * @param id 白名单主键
     * @return 白名单
     */
    @Override
    public WhitelistInfo selectWhitelistInfoById(Long id) {
        WhitelistInfo whitelistInfo = whitelistInfoMapper.selectWhitelistInfoById(id);
        // if (whitelistInfo.getServers() != null) {
        //     // whitelistInfo.getServers().split(",") 转Long数组
        //     List<String> collect = Arrays.stream(whitelistInfo.getServers().split(",")).collect(Collectors.toList());
        //     collect.remove("all");
        //     Long[] ids = new Long[collect.size()];
        //     for (String s : collect) {
        //         ids[collect.indexOf(s)] = Long.parseLong(s);
        //     }
        //     List<String> name = serverInfoMapper.selectServerNameByIds(ids);
        //     // name用，分割用于前端展示
        //     whitelistInfo.setServers(String.join(",", name));
        // }
        return whitelistInfo;
    }

    /**
     * 查询白名单列表
     *
     * @param whitelistInfo 白名单
     * @return 白名单
     */
    @Override
    public List<WhitelistInfo> selectWhitelistInfoList(WhitelistInfo whitelistInfo) {
        return whitelistInfoMapper.selectWhitelistInfoList(whitelistInfo);
    }

    /**
     * 新增白名单
     *
     * @param whitelistInfo 白名单
     * @return 结果
     */
    @Override
    public int insertWhitelistInfo(WhitelistInfo whitelistInfo) {
        return whitelistInfoMapper.insertWhitelistInfo(whitelistInfo);
    }

    /**
     * 修改白名单
     *
     * @param whitelistInfo 白名单
     * @return 结果
     */
    @Override
    public int updateWhitelistInfo(WhitelistInfo whitelistInfo) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!whitelistInfo.getAddState().isEmpty()) {
            String addState = whitelistInfo.getAddState();
            if (Boolean.parseBoolean(addState)) {
                whitelistInfo.setAddState("2");
                whitelistInfo.setStatus("0");
                whitelistInfo.setRemoveTime(new Date());
                whitelistInfo.setReviewUsers(name);
                try {
                    sendCommand(whitelistInfo, "whitelist remove " + whitelistInfo.getUserName());
                    try {
                        pushEmail.push(whitelistInfo.getQqNum().trim() + "@qq.com", EmailTemplate.TITLE,
                                "用户: " + whitelistInfo.getUserName() + " 的白名单申请已于 " + dateFormat.format(new Date()) + " 日被移除,原因: [" + whitelistInfo.getRemoveReason() + "] 审核人: " + name);
                    } catch (Exception e) {
                        log.error("发送邮件失败,请联系管理员!");
                        return 0;
                    }
                } catch (Exception e) {
                    log.error("移除白名单失败,请联系管理员!");
                    return 0;
                }
            } else {
                whitelistInfo.setAddState("0");
            }
        }
        if (!whitelistInfo.getStatus().isEmpty()) {
            if (whitelistInfo.getStatus().equals("1")) {
                if (whitelistInfo.getOnlineFlag() != 1) {
                    try {
                        sendCommand(whitelistInfo, "whitelist add " + whitelistInfo.getUserName());
                    } catch (Exception e) {
                        log.error("添加强制离线失败,请联系管理员!");
                        return 0;
                    }
                }
                whitelistInfo.setReviewUsers(name);
                try {
                    sendCommand(whitelistInfo, "whitelist add " + whitelistInfo.getUserName());
                    try {
                        pushEmail.push(whitelistInfo.getQqNum().trim() + "@qq.com", EmailTemplate.TITLE,
                                "用户: " + whitelistInfo.getUserName() + " 的白名单申请已于 " + dateFormat.format(new Date()) + " 日通过审核,审核人: " + name);
                    } catch (Exception e) {
                        log.error("发送邮件失败,请联系管理员!");
                        return 0;
                    }
                } catch (Exception e) {
                    log.error("添加白名单失败,请联系管理员!");
                    return 0;
                }
                whitelistInfo.setAddState("1");
                whitelistInfo.setAddTime(new Date());
            } else if (whitelistInfo.getStatus().equals("2")) {
                whitelistInfo.setReviewUsers(name);
                try {
                    pushEmail.push(whitelistInfo.getQqNum().trim() + "@qq.com", EmailTemplate.TITLE,
                            "用户: " + whitelistInfo.getUserName() + " 的白名单申请已于 " + dateFormat.format(new Date()) + " 日被拒绝,审核人: " + name);
                } catch (Exception e) {
                    log.error("发送邮件失败,请联系管理员!");
                    return 0;
                }
            }
        }
        return whitelistInfoMapper.updateWhitelistInfo(whitelistInfo);
    }

    /**
     * 批量删除白名单
     *
     * @param ids 需要删除的白名单主键
     * @return 结果
     */
    @Override
    public int deleteWhitelistInfoByIds(Long[] ids) {
        return whitelistInfoMapper.deleteWhitelistInfoByIds(ids);
    }

    /**
     * 删除白名单信息
     *
     * @param id 白名单主键
     * @return 结果
     */
    @Override
    public int deleteWhitelistInfoById(Long id) {
        return whitelistInfoMapper.deleteWhitelistInfoById(id);
    }

    /**
     * 查重
     *
     * @param whitelistInfo
     * @return
     */
    @Override
    public List<WhitelistInfo> checkRepeat(WhitelistInfo whitelistInfo) {
        return whitelistInfoMapper.checkRepeat(whitelistInfo);
    }

    /**
     * 发送Rcon命令 (弃用)
     *
     * @param command
     */
    private void sendCommand(WhitelistInfo info, String command) {
        if (info.getServers() != null) {
            // 如果servers包含all字样，则直接发送给所有服务器
            if (info.getServers().contains("all")) {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        RconUtil.sendCommand("all", command);
                    }
                };
                asyncManager.execute(task);
            } else {
                for (String key : info.getServers().split(",")) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            RconUtil.sendCommand(key, command);
                        }
                    };
                    asyncManager.execute(task);
                }
            }
        }
    }
}
