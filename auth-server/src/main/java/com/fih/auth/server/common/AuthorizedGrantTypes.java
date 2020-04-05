package com.fih.auth.server.common;

public enum AuthorizedGrantTypes {

    PWD("pwd"),

    SMS("sms"),

    REFRESH_TOKEN("refresh_token"),

    AUTHORIZATION_CODE("authorization_code"),

    PASSWORD("password");


    private String val;

    public String getVal() {
        return val;
    }

    private AuthorizedGrantTypes(String val){
        this.val=val;
    }
}
