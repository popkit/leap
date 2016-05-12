package org.popkit.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Author: aborn.jiang
 * Email : aborn.jiang@gmail.com
 * Date  : 05-12-2016
 * Time  : 10:07 PM
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext ctx = null;

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
