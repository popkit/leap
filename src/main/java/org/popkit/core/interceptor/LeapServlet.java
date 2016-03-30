package org.popkit.core.interceptor;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Author: Aborn Jiang
 * Email : aborn.jiang@gmail.com
 * Date  : 03-17-2016
 * Time  : 11:07 AM
 */
public class LeapServlet extends DispatcherServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String str = req.getLocalName();
        super.service(req, res);
    }
}
