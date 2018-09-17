package com.bkmc.messengerconnector.communicate.kakaotalk;

import com.bkmc.messengerconnector.communicate.IController;
import com.bkmc.messengerconnector.config.Messenger;
import com.bkmc.messengerconnector.message.BasicMessage;
import com.bkmc.messengerconnector.message.Message;
import com.bkmc.messengerconnector.messagehub.EntireMessageHub;
import com.bkmc.messengerconnector.messagehub.KakaotalkMessageHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
public class KakaotalkController implements IController {

    @Autowired
    EntireMessageHub entireMessageHub;

    @Autowired
    KakaotalkMessageHub kakaotalkMessageHub;

    @RequestMapping("/kakaotalk/receive")
    public String kakaotalkReceiver(HttpServletRequest request) {
        String channelFrom = request.getParameter("channel_from");
        String sender = request.getParameter("sender");
        String context = request.getParameter("context");

        Message msgDTO = new BasicMessage();
        msgDTO.setMessenger(Messenger.KAKAOTALK);
        msgDTO.setChannelFrom(channelFrom);
        msgDTO.setSender(sender);
        msgDTO.setContext(context);

        entireMessageHub.addMessage(msgDTO);
        System.out.println("Success inserting kakaotalk message to messagehub");

        return "Success";
    }

    @RequestMapping("/kakaotalk/send")
    public ArrayList getKakaotalkMessages() {
        ArrayList<Message> msgData = kakaotalkMessageHub.pollAllMessage();
        return formattingProcess(msgData);
    }

    private ArrayList formattingProcess(ArrayList<Message> msgDTOs) {
        ArrayList<HashMap> msgList = new ArrayList<>();
        HashMap<String, String> msg = new HashMap<>();

        for (Message message : msgDTOs) {
            msg.put("messenger", message.getMessenger().name());
            msg.put("channel_from", message.getChannelFrom());
            msg.put("sender", message.getSender());
            msg.put("context", message.getContext());
            msg.put("date", message.getDate().toString());

            msgList.add(msg);
        }

        return msgList;
    }
}
