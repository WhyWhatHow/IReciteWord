package com.sdut.soft.ireciteword.bean;

/**
 * 单词词汇大类：
 */
public class Unit {

    private int mKey;
    private long mTime;
    private String mMetaKey;

    public Unit() {
    }

    public Unit(int key, long time, String metaKey) {
        mKey = key;
        mTime = time;
        mMetaKey = metaKey;
    }

    public int getKey() {
        return mKey;
    }

    public void setKey(int key) {
        mKey = key;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getMetaKey() {
        return mMetaKey;
    }

    public void setMetaKey(String metaKey) {
        mMetaKey = metaKey;
    }

}
