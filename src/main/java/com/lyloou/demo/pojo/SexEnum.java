package com.lyloou.demo.pojo;

public enum SexEnum {
    FEMALE, MALE;

    public static SexEnum getSexByCode(int index) {
        return SexEnum.values()[index];
    }

    public int getCode() {
        return this.ordinal();
    }

    public String getName() {
        return this.name();
    }
}
