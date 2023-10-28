package com.spantanTech.leonidas.security.ENUM;

public enum ACTION {

    VIEW("view"),
    UPDATE("update"),

    CREATE("create"),

    INATIVE("inative");


    private String action;

    ACTION(String action){
        this.action = action;
    }

    public String getKey(){
        return action;
    }
}
