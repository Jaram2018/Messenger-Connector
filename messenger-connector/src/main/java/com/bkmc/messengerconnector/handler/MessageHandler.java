package com.bkmc.messengerconnector.handler;

import com.bkmc.messengerconnector.messagehub.InMessageHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-16
 * Time: 오후 5:28
 */
@Service
public class MessageHandler implements IMessageHandler {

    @Autowired
    InMessageHub inMessageHub;

    @Override
    public ArrayList execute() {
        return inMessageHub.pollAllMessage();
    }
}
