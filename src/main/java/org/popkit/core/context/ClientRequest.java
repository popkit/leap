package org.popkit.core.context;

/**
 * 客户端的请求
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:15:04
 */
public class ClientRequest {

    private String requestId;
    private String refferRequestId;
    private String userIp;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRefferRequestId() {
        return refferRequestId;
    }

    public void setRefferRequestId(String refferRequestId) {
        this.refferRequestId = refferRequestId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
