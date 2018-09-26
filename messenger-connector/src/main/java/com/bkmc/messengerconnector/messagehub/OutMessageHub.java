package com.bkmc.messengerconnector.messagehub;

import com.bkmc.messengerconnector.message.Message;
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
public class OutMessageHub implements IMessageHub {

    private Queue<Message> outMsgQ = new ArrayDeque<>();
    private Map<String, IMessageHub> messageHubMap = new HashMap<>();

    public IMessageHub hubMapPutElement(String messenger, IMessageHub messageHub) {
        return messageHubMap.put(messenger, messageHub);
    }

    public IMessageHub hubMapRemoveElement(String messenger, IMessageHub messageHub) {
        return messageHubMap.remove(messenger);
    }

    @Override
    public boolean addMessage(Message message) {
        // If exists messenger that is contained in 'messengerTo', insert the message to message-hub
        for (String messenger : message.getMessengerTo()) {
            messageHubMap.get(messenger).addMessage(message);
        }
        return true;
    }

    @Override
    public Message pollMessage() {
        return outMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();
        int size = outMsgQ.size();

        for (int i=0; i<size; i++) {
            Message message = outMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
