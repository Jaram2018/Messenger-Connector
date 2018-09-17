package com.bkmc.messengerconnector.messagehub;

import com.bkmc.messengerconnector.message.Message;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 6:28
 */
public interface IMessageHub {

    boolean addMessage(Message message);
    Message pollMessage();
    ArrayList pollAllMessage();
}
