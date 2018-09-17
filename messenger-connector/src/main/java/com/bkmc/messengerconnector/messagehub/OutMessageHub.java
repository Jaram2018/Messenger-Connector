package com.bkmc.messengerconnector.messagehub;

import com.bkmc.messengerconnector.config.Messenger;
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
    private Map<Messenger, IMessageHub> hubMap = new HashMap<>();

    public IMessageHub hubMapPutElement(Messenger messenger, IMessageHub messageHub) {
        return hubMap.put(messenger, messageHub);
    }

    public IMessageHub hubMapRemoveElement(Messenger messenger, IMessageHub messageHub) {
        return hubMap.remove(messenger);
    }

    @Override
    public boolean addMessage(Message message) {
        // If these aren't same messenger, put the messagehub to messageHub
        for (Messenger messenger : hubMap.keySet()) {
            hubMap.get(messenger).addMessage(message);
//            if (message.getMessenger() != messenger)
//                hubMap.get(messenger).addMessage(message);
        }
        return outMsgQ.add(message);
    }

    @Override
    public Message pollMessage() {
        return outMsgQ.poll();
    }

    @Override
    public ArrayList pollAllMessage() {
        ArrayList<Message> messages = new ArrayList<>();

        for (int i = 0; i< outMsgQ.size(); i++) {
            Message message = outMsgQ.poll();
            messages.add(message);
        }

        return messages;
    }
}
