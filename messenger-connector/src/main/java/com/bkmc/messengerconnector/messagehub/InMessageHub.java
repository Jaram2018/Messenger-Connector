package com.bkmc.messengerconnector.messagehub;

import com.bkmc.messengerconnector.handler.MessageHandler;
import com.bkmc.messengerconnector.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-16
 * Time: 오후 2:19
 */
@Service
public class InMessageHub implements IMessageHub {

    private Queue<Message> inMsgQ = new ArrayDeque<>();

    @Autowired
    OutMessageHub outMessageHub;

    @Autowired
    MessageHandler messageHandler;

    // 이 부분을 쓰레드로 만들면 좋읋 것 같은데...
    // 1 초 정도마다 큐에 저장된 모든 메세지를 처리하게끔 ...
    public void handlingMessage() {
        Queue handledMessageQueue = messageHandler.execute(inMsgQ);

        while (!handledMessageQueue.isEmpty()) {
            outMessageHub.addMessage((Message) handledMessageQueue.poll());
        }
    }

    @Override
    public boolean addMessage(Message message) {
        return inMsgQ.add(message);
    }

    @Override
    public Message pollMessage() {
        return inMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();
        int size = inMsgQ.size();

        for (int i=0; i<size; i++) {
            Message message = inMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
