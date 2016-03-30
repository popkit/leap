package org.popkit.core.context.impl;

import org.popkit.core.context.ClientEnv;
import org.popkit.core.context.ClientHeader;
import org.popkit.core.context.LeapContext;
import org.popkit.core.context.ClientRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:15:49
 */
public class DefaultLeapContext implements LeapContext {

    // requeset里的参数
    private Map<String, String> parametersMap = new HashMap<String, String>();

    private HttpServletRequest servletRequest;

    public ClientHeader getClientHeader() {
        return null;
    }

    public ClientEnv getClientEnv() {
        return null;
    }

    private ClientRequest clientRequest = new ClientRequest();

    public DefaultLeapContext(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getParameter(String paramName) {
        return this.parametersMap.get(paramName);
    }

    public void addParameter(String key, String value) {
        this.parametersMap.put(key, value);
    }

    public Map<String, String> getParametersMap() {
        return parametersMap;
    }

    public void setParametersMap(Map<String, String> parametersMap) {
        this.parametersMap = parametersMap;
    }


    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }
}
