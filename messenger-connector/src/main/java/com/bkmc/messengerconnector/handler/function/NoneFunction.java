package com.bkmc.messengerconnector.handler.function;

import com.bkmc.messengerconnector.message.Message;


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
    public Message getHandledMessage() {
        // default
        sendToOtherMessengers();
        return message;
    }
}
