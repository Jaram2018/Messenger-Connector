package com.bkmc.messengerconnector.config;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 5:17
 */
public enum Messenger {

    ADMIN("ADMIN"),
    KAKAOTALK("KAKAOTALK"),
    SLACK("SLACK"),
    NONE("NONE"),
    ;

    private String value;

    Messenger(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
