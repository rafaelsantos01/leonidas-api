package com.spantanTech.leonidas.security.ENUM;

public enum PERMISSION {
    ORGANIZATION("Organization"),
    USER("Users"),
    STOCK("Stock"),
    ADMIN("Admin");


    private String permission;

    PERMISSION(String permission){
        this.permission = permission;
    }

    public String getKey(){
        return permission;
    }
}
