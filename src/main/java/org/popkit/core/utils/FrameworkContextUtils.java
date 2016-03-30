package org.popkit.core.utils;

import org.popkit.core.context.FrameworkContext;
import org.popkit.core.context.LeapContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:16:09
 */
public class FrameworkContextUtils {
    private static ThreadLocal<FrameworkContext> frameworkContextThreadLocal = new ThreadLocal<FrameworkContext>();

    public static FrameworkContext getFrameworkContext() {
        return frameworkContextThreadLocal.get();
    }

    public static void setFrameworkContextThreadLocal(FrameworkContext frameworkContext) {
        // 设置servletContext
        frameworkContext.setServletContext(getServletContext(frameworkContext.getRequest()));
        frameworkContextThreadLocal.set(frameworkContext);
    }

    public static void setLeapContext(LeapContext context) {
        frameworkContextThreadLocal.get().setLeapContext(context);
    }

    public static void clear() {
        frameworkContextThreadLocal.remove();
    }

    public static ServletContext getServletContext(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        return servletContext;
    }

    /**
     * 提供判断是否是框架支持的请求
     *
     * @return
     */
    public static boolean isMobileSupport() {
        return (null != getFrameworkContext());
    }
}
