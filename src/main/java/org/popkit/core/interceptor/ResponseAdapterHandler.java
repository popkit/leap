package org.popkit.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.popkit.core.utils.FrameworkContextUtils;
import org.popkit.core.utils.ResponseUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:14:25
 */
public class ResponseAdapterHandler implements HandlerMethodReturnValueHandler {

    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {
        HttpServletResponse response = FrameworkContextUtils.getFrameworkContext().getResponse();

        if (null == returnValue || returnValue instanceof String) {
            // do nothing
        } else {
            // TODO 默认返回 JSON格式
            ResponseUtils.renderJson(response, JSON.toJSONString(returnValue, SerializerFeature.BrowserCompatible));
        }
    }
}
