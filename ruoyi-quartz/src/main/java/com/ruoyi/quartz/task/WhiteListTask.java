package com.ruoyi.quartz.task;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.server.common.PushEmail;
import com.ruoyi.server.domain.WhitelistInfo;
import com.ruoyi.server.service.IWhitelistInfoService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: WhiteListTask <br>
 * Description:
 * date: 2023/12/27 20:13 <br>
 *
 * @author zhangchanggeng <br>
 * @since JDK 1.8
 */
@Component("whiteListTask")
public class WhiteListTask {

    @Autowired
    private IWhitelistInfoService whitelistInfoService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PushEmail pushEmail;


    /**
     * 定时任务调度
     * 白名单同步
     */
    public void polling() {
        // 查询未审核白名单
        WhitelistInfo whitelistInfo = new WhitelistInfo();
        whitelistInfo.setStatus("0");
        List<WhitelistInfo> whitelistInfos = whitelistInfoService.selectWhitelistInfoList(whitelistInfo);
        if (whitelistInfos.isEmpty()) {
            return;
        }
        List<SysUser> list;
        // 缓存用户数据
        if (redisCache.getCacheObject("userList") == null) {
            // 查询管理员用户邮箱
            list = userService.selectUserList(new SysUser());
            // 缓存1天
            redisCache.setCacheObject("userList", list, 1, TimeUnit.DAYS);
        } else {
            list = redisCache.getCacheObject("userList");
        }
        // 发邮件通知
        if (list != null && !list.isEmpty()) {
            for (SysUser sysUser : list) {
                try {
                    if (sysUser.getEmail() != null && !sysUser.getEmail().isEmpty()) {
                        pushEmail.push(sysUser.getEmail(), "白名单审核", "有新的白名单需要审核");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
