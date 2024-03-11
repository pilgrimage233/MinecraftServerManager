package com.ruoyi.server.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 域名转IP
 * 作者：Memory
 */
public class DomainToIp {
    public static String domainToIp(String domain) {
        if (domain.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)")) {
            return domain;
        }
        InetAddress address = null;
        try {
            address = InetAddress.getByName(domain);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Domain: " + domain);
        System.out.println("IP Address: " + address.getHostAddress());
        return address.getHostAddress();
    }
}
