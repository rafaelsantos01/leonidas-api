package com.spantanTech.leonidas.modules.templatesEmail.ENUM;

public enum TEMPLATETYPE {
    NEWUSER(0),
    RECOVERYPASSWORD(1),
    CHANGEPASSWORD(2);

    private int template;

    TEMPLATETYPE(int role){
        this.template = role;
    }

    public int getKey(){
        return template;
    }
}
