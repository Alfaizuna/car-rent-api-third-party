package com.finals.carrentapi.enums;

public enum CarType {
    MANUAL("manual"), AUTOMATIC("automatic");

    private String desc;

    CarType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
