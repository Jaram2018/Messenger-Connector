package com.bkmc.messengerconnector.handler.function;

import com.bkmc.messengerconnector.message.Message;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-17
 * Time: 오후 11:27
 */
public abstract class Function {

    protected String[] contextArray;
    protected Message message;

    public void setContextArray(String[] contextArray) {
        this.contextArray = contextArray;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public abstract Message getHandledMessage(String[] contextArray);
}
