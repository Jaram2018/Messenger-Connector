package com.bkmc.messengerconnector.messagehub.messengerhub;

import com.bkmc.messengerconnector.message.Message;
import com.bkmc.messengerconnector.messagehub.IMessageHub;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-22
 * Time: 오후 7:58
 */
@Service
public class AdminMessageHub implements IMessageHub {

    private Queue<Message> adminMsgQ = new ArrayDeque<>();

    @Override
    public boolean addMessage(Message message) {
        return adminMsgQ.add(message);
    }

    @Override
    public Message pollMessage() {
        return adminMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();
        int size = adminMsgQ.size();

        for (int i=0; i<size; i++) {
            Message message = adminMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
