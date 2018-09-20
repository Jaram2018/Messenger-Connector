package com.bkmc.messengerconnector.config;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-17
 * Time: 오후 8:27
 */
public enum Command {

    ANNOTATION('@'),
    EXCLAMATION('!'),
    ;

    private Character value;

    Command(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}
