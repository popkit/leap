package org.popkit.core.enums;

/**
 * 平台
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-03-20:21:35
 */
public enum Platform {
    IOS(1, "iOS"),
    ANDROID(2, "android"),
    ;

    private int value;
    private String name;

    Platform(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
