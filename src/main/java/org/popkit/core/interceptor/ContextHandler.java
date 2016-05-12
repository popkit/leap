package org.popkit.core.interceptor;

import org.popkit.core.annotation.LeapSupport;
import org.popkit.core.context.FrameworkContext;
import org.popkit.core.context.ClientRequest;
import org.popkit.core.context.impl.DefaultLeapContext;
import org.popkit.core.utils.FrameworkContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 处理上下文
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:14:47
 */
public class ContextHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                LeapSupport support = handlerMethod.getMethod().getAnnotation(LeapSupport.class);
                LeapSupport leapSupportOnClazz = handlerMethod.getBeanType().getAnnotation(LeapSupport.class);

                // 无论怎样都要有frameworkcontext
                FrameworkContext frameworkContext = new FrameworkContext();
                frameworkContext.setRequest(request);
                frameworkContext.setResponse(response);
                frameworkContext.setAction(handlerMethod.getMethod());
                FrameworkContextUtils.setFrameworkContextThreadLocal(frameworkContext);

                if (null != support || null != leapSupportOnClazz) {
                    DefaultLeapContext leapContext = new DefaultLeapContext(request);

                    // 将参数存入map
                    Enumeration<String> params = request.getParameterNames();
                    while (params.hasMoreElements()) {
                        String key = params.nextElement();
                        String value = request.getParameter(key);
                        leapContext.addParameter(key.toLowerCase(), value);
                    }

                    ClientRequest clientRequest = leapContext.getClientRequest();
                    clientRequest.setRequestId(request.getRequestedSessionId());

                    // TODO context 及 版本的解析还没做
                    FrameworkContextUtils.setLeapContext(leapContext);
                }
            }

        } catch (Exception e) {

        }

        return true;
        //return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
