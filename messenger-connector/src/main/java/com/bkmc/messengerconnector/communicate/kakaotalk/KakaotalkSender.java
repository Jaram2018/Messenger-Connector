package com.bkmc.messengerconnector.communicate.kakaotalk;

import com.bkmc.messengerconnector.message.Message;
import com.bkmc.messengerconnector.messagehub.KakaotalkMessageHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Project: communicate-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 5:19
 */
@RestController
public class KakaotalkSender {

    @Autowired
    KakaotalkMessageHub kakaotalkMessageHub;

    @RequestMapping("/getKakaotalkMessages")
    public HashMap<String, String> getKakaotalkMessages() {
        ArrayList<Message> msgData;
        msgData = kakaotalkMessageHub.pollAllMessage();
        return toHashMapProcess(msgData);
    }

    private HashMap<String, String> toHashMapProcess(ArrayList<Message> msgDTOs) {
        HashMap<String, String> msgOut = new HashMap<>();

        for (Message message : msgDTOs) {
            msgOut.put("messenger", message.getMessenger().name());
            msgOut.put("channel_from", message.getChannelFrom());
            msgOut.put("sender", message.getSender());
            msgOut.put("context", message.getContext());
            msgOut.put("date", message.getDate().toString());
        }

        return msgOut;
    }
}
