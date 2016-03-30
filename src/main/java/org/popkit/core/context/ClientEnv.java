package org.popkit.core.context;

import org.popkit.core.enums.ClientType;
import org.popkit.core.enums.Platform;

/**
 * 客户端的环境参数
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:21:33
 */
public class ClientEnv {

    private ClientType clientType;

    private Platform platform;

    // 客户版app版本
    private String version;

    // 客户端系统版本, iOS 9.2 这种
    private String platVersion;

    // 协议版本
    private String apiVersion;

    // app的发行渠道
    private String source;

    // 客户端的设备信息
    private String deviceInfo;

}
