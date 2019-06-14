package com.sdut.soft.ireciteword.bean;

public class MetaOption {
    Integer icon;
    String meta;

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public MetaOption(Integer icon, String meta) {
        this.icon = icon;
        this.meta = meta;
    }

    public MetaOption() {

    }
}
