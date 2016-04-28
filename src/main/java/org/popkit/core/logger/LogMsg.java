package org.popkit.core.logger;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-04-28:22:01
 */
public class LogMsg {
    private StringBuilder sb;

    public static LogMsg create(String title) {
        return new LogMsg(title);
    }

    public LogMsg(String title) {
        sb = new StringBuilder("L=" + title);
    }

    public LogMsg add(String key, Object value) {
        sb.append("&" + key + "=" + value);
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
