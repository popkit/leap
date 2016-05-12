package org.popkit.core.interceptor;

import org.popkit.core.annotation.LeapRequest;
import org.popkit.core.beans.DefaultRequestBuilder;
import org.popkit.core.context.FrameworkContext;
import org.popkit.core.context.LeapContext;
import org.popkit.core.utils.FrameworkContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Modifier;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:14:28
 */
public class ArgumentsHandler implements HandlerMethodArgumentResolver {

    @Autowired
    private DefaultRequestBuilder defaultRequestBuilder;

    public boolean supportsParameter(MethodParameter parameter) {
        return shouldResolve(parameter);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LeapContext context = FrameworkContextUtils.getFrameworkContext().getLeapContext();
        Class clazz = parameter.getParameterType();

        // 注入LeapContext上下文
        if (LeapContext.class.isAssignableFrom(clazz)) {
            return FrameworkContextUtils.getFrameworkContext().getLeapContext();
        }

        if (Modifier.isInterface(clazz.getModifiers()) || Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }

        // 最后注入默认的普通的参数
        return defaultRequestBuilder.instantiate(context, parameter.getParameterType());
    }

    private boolean shouldResolve(MethodParameter parameter) {
        FrameworkContext frameworkContext = FrameworkContextUtils.getFrameworkContext();
        if (null == frameworkContext) {
            return false;
        }

        Class clazz = parameter.getParameterType();
        if (LeapContext.class.isAssignableFrom(clazz)) {
            return true;
        }

        return null != clazz.getAnnotation(LeapRequest.class);
    }
}
