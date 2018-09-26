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
 * Time: 오후 6:32
 */
@Service
public class SlackMessageHub implements IMessageHub {

    private Queue<Message> slackMsgQ = new ArrayDeque<>();

    @Override
    public boolean addMessage(Message message) {
        return slackMsgQ.add(message);
    }

    @Override
    public Message pollMessage() {
        return slackMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();
        int size = slackMsgQ.size();

        for (int i=0; i<size; i++) {
            Message message = slackMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
