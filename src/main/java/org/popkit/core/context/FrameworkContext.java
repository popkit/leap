package org.popkit.core.context;

import org.popkit.core.enums.LeapProtocol;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:15:03
 */
public class FrameworkContext {
    private HttpServletResponse response;
    private HttpServletRequest request;
    private ServletContext servletContext;


    private Method action;
    private LeapProtocol leapProtocol;
    private LeapContext leapContext;


    private boolean compressed = true;
    private boolean encrypted = true;

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public LeapProtocol getLeapProtocol() {
        return leapProtocol;
    }

    public void setLeapProtocol(LeapProtocol leapProtocol) {
        this.leapProtocol = leapProtocol;
    }

    public LeapContext getLeapContext() {
        return leapContext;
    }

    public void setLeapContext(LeapContext leapContext) {
        this.leapContext = leapContext;
    }
}
