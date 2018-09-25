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
 * Time: 오후 11:39
 */
public class NoneFunction extends Function {

    @Override
    public Message getHandledMessage(String[] contextArray) {
        // default
        sendToOtherMessengers();
        return message;
    }

    private void sendToOtherMessengers() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        String messengerFrom = message.getMessengerFrom();

        for (Messenger value : Messenger.values()) {
            if (!value.getValue().equals(Messenger.NONE.getValue()) && !value.getValue().equals(messengerFrom)) {
                messengerTo.add(value.getValue());
            }
        }
    }

    private void sendToKakaotalk() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        messengerTo.add(Messenger.KAKAOTALK.getValue());
    }

    private void sendToSlack() {
        ArrayList<String> messengerTo = message.getMessengerTo();
        messengerTo.add(Messenger.SLACK.getValue());
    }
}
