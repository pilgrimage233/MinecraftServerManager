package com.ruoyi.server.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务器信息对象 server_info
 *
 * @author ruoyi
 * @date 2024-03-10
 */
public class ServerInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 随机UUID
     */
    @Excel(name = "随机UUID")
    private String uuid;

    /**
     * 服务器名称标签
     */
    @Excel(name = "服务器名称标签")
    private String nameTag;

    /**
     * 服务器IP
     */
    @Excel(name = "服务器IP")
    private String ip;

    /**
     * RCON远程端口号
     */
    @Excel(name = "RCON远程端口号")
    private Long rconPort;

    /**
     * 远程密码/MD5加密
     */
    @Excel(name = "远程密码/MD5加密")
    private String rconPassword;

    /**
     * 启用状态
     */
    @Excel(name = "启用状态")
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getRconPort() {
        return rconPort;
    }

    public void setRconPort(Long rconPort) {
        this.rconPort = rconPort;
    }

    public String getRconPassword() {
        return rconPassword;
    }

    public void setRconPassword(String rconPassword) {
        this.rconPassword = rconPassword;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("uuid", getUuid())
                .append("nameTag", getNameTag())
                .append("ip", getIp())
                .append("rconPort", getRconPort())
                .append("rconPassword", getRconPassword())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("status", getStatus())
                .append("remark", getRemark())
                .toString();
    }
}
