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
 * Date: 2018-09-09
 * Time: 오후 6:31
 */
@Service
public class KakaotalkMessageHub implements IMessageHub {

    private Queue<Message> kakaotalkMsgQ = new ArrayDeque<>();

    @Override
    public boolean addMessage(Message message) {
        return kakaotalkMsgQ.add(message);
    }

    @Override
    public Message pollMessage() {
        return kakaotalkMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();

        for (int i=0; i<kakaotalkMsgQ.size(); i++) {
            Message message = kakaotalkMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
