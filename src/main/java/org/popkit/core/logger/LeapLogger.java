package org.popkit.core.logger;

import org.slf4j.LoggerFactory;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-04-28:22:01
 */
public class LeapLogger {
    //private static final LeapLogger LOG = LeapLoggerFactory.getLogger("api-sys-monitor");

    private LeapLogger() {}

    /**
     * 各类非核心流程 抛Exception 情况
     * @param msg
     * @param t
     */
    public static void warn(LogMsg msg, Throwable t) {
        //LOG.warn(msg.toString(), t);
    }

    /**
     * 各类非核心流程 抛Exception 情况
     * @param msg
     * @param t
     */
    public static void warn(String msg, Throwable t) {
        //LOG.warn(msg, t);
    }
}
