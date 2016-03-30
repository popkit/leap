package org.popkit.core.context;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:15:03
 */
public interface LeapContext {

    ClientRequest getClientRequest();

    String getParameter(String paramName);

    ClientHeader getClientHeader();

    ClientEnv getClientEnv();
}
