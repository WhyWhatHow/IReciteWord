package com.sdut.soft.ireciteword.bean;

import java.util.List;

public class TranslateResultBody {

    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResult>> translateResult;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setTranslateResult(List<List<TranslateResult>> translateResult) {
        this.translateResult = translateResult;
    }
    public List<List<TranslateResult>> getTranslateResult() {
        return translateResult;
    }

}