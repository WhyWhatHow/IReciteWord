package com.sdut.soft.ireciteword.bean;

public class SettingOption {
    String name;
    Class targetActivity;

    public SettingOption() {
    }

    public SettingOption(String name, Class targetActivity) {

        this.name = name;
        this.targetActivity = targetActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(Class targetActivity) {
        this.targetActivity = targetActivity;
    }
}
