package org.popkit.core.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.popkit.core.logger.LeapLogger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-08:10:14
 */
@Service
public class LeapConfigLoader {
    private static final int TIME_OUT = 1000*60;   // 1-minuts
    private static final String CONFIG_FILE_UNIX = "/data/webapps/";
    private static final String CONFIG_FILE_WINDOWS = "D:/data/webapps/";
    private static final String APPKIT_CONFIG_FILE_NAME = "leap.conf";
    private static final ExecutorService SYNC_EXECUTOR_SERVICE = Executors.newFixedThreadPool(1);
    private static AtomicBoolean STATUS = new AtomicBoolean(false);
    private static Date LAST_UPDATE_TIME = new Date();

    private static final ConcurrentHashMap<String, String> config = new ConcurrentHashMap<String, String>();

    @PostConstruct
    private void init() {
        sync();
    }

    public static String getWebappsRoot() {
        return SystemUtils.IS_OS_WINDOWS ?
                CONFIG_FILE_WINDOWS :
                CONFIG_FILE_UNIX;
    }

    public static String get(String key) {
        if (new Date().getTime() - LAST_UPDATE_TIME.getTime() > TIME_OUT) {
            SYNC_EXECUTOR_SERVICE.execute(new Runnable() {
                public void run() {
                    sync();
                }
            });
        }

        return config.get(key);
    }

    private static void sync() {
        // Thread safety
        if (!STATUS.compareAndSet(false, true)) {
            return;
        }

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
            LeapLogger.error("error in sync!", e);
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LAST_UPDATE_TIME = new Date();
            STATUS.compareAndSet(true, false);
        }
    }

    public static void main(String[] args) {
        String imageRoot = get("anno_root");
        System.out.println("imageRoot=" + imageRoot);
    }
}
