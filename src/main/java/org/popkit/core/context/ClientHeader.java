package org.popkit.core.context;

import java.util.Map;

/**
 * 客户端的header参数
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:21:32
 */
public class ClientHeader {
    // UA信息
    private String userAgent;

    private String token;

    // 统一编号
    private String unionId;

    // 其他参数
    private Map<String, String> headParamMap;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Map<String, String> getHeadParamMap() {
        return headParamMap;
    }

    public void setHeadParamMap(Map<String, String> headParamMap) {
        this.headParamMap = headParamMap;
    }
}
