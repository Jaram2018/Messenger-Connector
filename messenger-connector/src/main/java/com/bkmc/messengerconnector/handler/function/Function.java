package com.bkmc.messengerconnector.handler.function;

import com.bkmc.messengerconnector.config.Messenger;
import com.bkmc.messengerconnector.message.Message;

import java.util.ArrayList;

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

    public abstract Message getHandledMessage();

    protected void sendToOtherMessengers() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        String messengerFrom = message.getMessengerFrom();

        for (Messenger value : Messenger.values()) {
            if (!value.getValue().equals(Messenger.NONE.getValue()) && !value.getValue().equals(messengerFrom)) {
                messengerTo.add(value.getValue());
            }
        }
    }

    protected void sendToKakaotalk() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        messengerTo.add(Messenger.KAKAOTALK.getValue());
    }

    protected void sendToSlack() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        messengerTo.add(Messenger.SLACK.getValue());
    }
}
