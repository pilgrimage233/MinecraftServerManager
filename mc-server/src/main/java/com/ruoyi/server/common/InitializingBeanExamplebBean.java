package com.ruoyi.server.common;

import com.github.t9t.minecraftrconclient.RconClient;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.server.domain.ServerInfo;
import com.ruoyi.server.service.IServerInfoService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class InitializingBeanExamplebBean implements InitializingBean {
    private static final Log log = LogFactory.getLog(InitializingBeanExamplebBean.class);
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IServerInfoService serverInfoService;

    /**
     * InitializingBean afterPropertiesSet
     * 在bean初始化后执行一些操作
     * 例如：初始化服务器信息缓存、Rcon连接
     * 作者：Memory
     */
    @Override
    public void afterPropertiesSet() {
        log.debug("InitializingBean afterPropertiesSet begin...");
        // 判断Redis缓存是否存在
        // if (redisCache.hasKey("serverInfo") && redisCache.hasKey("serverInfoUpdateTime")) {
        //     // 判断上次缓存时间是否超过一天
        //     if (DateUtils.getNowDate().getTime() - ((Date) redisCache.getCacheObject("serverInfoUpdateTime")).getTime() < 86400000) {
        //         log.debug("服务器信息缓存存在且未过期");
        //         return;
        //     }
        // }
        // 服务器信息缓存
        redisCache.setCacheObject("serverInfo", serverInfoService.selectServerInfoList(new ServerInfo()), 1, TimeUnit.DAYS);
        // 服务器信息缓存更新时间
        redisCache.setCacheObject("serverInfoUpdateTime", DateUtils.getNowDate());
        // 初始化Rcon连接
        ServerInfo info = new ServerInfo();
        info.setStatus(1L);
        for (ServerInfo serverInfo : serverInfoService.selectServerInfoList(info)) {
            try {
                log.debug("初始化Rcon连接：" + serverInfo.getNameTag());
                MapCache.put(serverInfo.getId().toString(), RconClient.open(DomainToIp.domainToIp(serverInfo.getIp()), serverInfo.getRconPort().intValue(), serverInfo.getRconPassword()));
                // 打印Map缓存
                // System.out.println(MapCache.getMap().toString());
                log.debug("初始化Rcon连接成功：" + serverInfo.getNameTag());
            } catch (Exception e) {
                log.error("初始化Rcon连接失败：" + serverInfo.getNameTag() + " " + serverInfo.getIp() + " " + serverInfo.getRconPort() + " " + serverInfo.getRconPassword());
                log.error("失败原因：" + e.getMessage());
            }
            System.out.println(MapCache.getMap());
        }
        log.debug("InitializingBean afterPropertiesSet end...");

        // 全局发送广播提示
        for (Map.Entry<String, RconClient> stringRconClientEntry : MapCache.getMap().entrySet()) {
            RconUtil.sendCommand(stringRconClientEntry.getKey(), "say Rcon ready! Time: " + DateUtils.getNowDate());
        }

    }
}
