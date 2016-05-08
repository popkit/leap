package org.popkit.core.config;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.popkit.core.logger.LeapLogger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-08:10:14
 */
@Service
public class LeapConfigLoader {
    private static final String CONFIG_FILE_UNIX = "/data/webapps/";
    private static final String CONFIG_FILE_WINDOWS = "D:/data/webapps/";
    private static final String APPKIT_CONFIG_FILE_NAME = "leap.conf";

    private static final ConcurrentHashMap<String, String> config = new ConcurrentHashMap<String, String>();

    @PostConstruct
    private void init() {
        updateConfigMap();
    }

    public static String getWebappsRoot() {
        return SystemUtils.IS_OS_WINDOWS ?
                CONFIG_FILE_WINDOWS :
                CONFIG_FILE_UNIX;
    }

    public static String get(String key) {
        updateConfigMap();
        return config.get(key);
    }

    private static void updateConfigMap() {
        new Thread(new Runnable() {
            public void run() {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(getWebappsRoot() + APPKIT_CONFIG_FILE_NAME));
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        if (StringUtils.isNotBlank(sCurrentLine) && (!sCurrentLine.trim().startsWith("#"))) {
                            String[] keyValuePair = sCurrentLine.split("=");
                            if (keyValuePair.length > 1) {
                                config.put(keyValuePair[0].trim(), keyValuePair[1].trim());
                            }
                        }
                    }
                } catch (IOException e) {
                    LeapLogger.error("error", e);
                    e.printStackTrace();
                } finally {
                    try {
                        if (br != null)br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
