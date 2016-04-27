package org.popkit.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.popkit.core.entity.CommonResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Author: Aborn Jiang
 * Email : aborn.jiang@gmail.com
 * Date  : 03-17-2016
 * Time  : 11:07 AM
 */
public class ResponseUtils {
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain", text);
    }

    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml", text);
    }

    public static void renderHtml(HttpServletResponse response, String text) {
        render(response, "text/html", text);
    }

    public static void renderJavaScript(HttpServletResponse response, String text) {
        render(response, "text/javascript", text);
    }

    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "application/json", text);
    }

    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType + "; charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        Writer writer = null;
        try {
            writer = response.getWriter();
            response.getWriter().write(text);
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public static void render(CommonResponse comRes) {
        Writer writer = null;
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes();
            HttpServletResponse response = attr.getResponse();

            String encoding = response.getCharacterEncoding();
            if (StringUtils.isEmpty(encoding)) {
                encoding = "utf-8";
                response.setCharacterEncoding(encoding);
            }

            // 这里不能设置为 application/json, 否则会返回
            // "<pre style="word-wrap: break-word; white-space: pre-wrap;">{"statusCode":200,"statusInfo":"\u4E0A\u4F20\u6210\u529F,\u6587\u4EF6\u540D\u4E3A:airobot87x87.png"}</pre>"
            // TODO 这个问题要细挖下
            response.setContentType("text/html;charset=" + encoding);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache,no-store");
            response.setDateHeader("Expires", 0);

            writer = response.getWriter();
            writer.write(JSON.toJSONString(comRes, SerializerFeature.BrowserCompatible));
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
