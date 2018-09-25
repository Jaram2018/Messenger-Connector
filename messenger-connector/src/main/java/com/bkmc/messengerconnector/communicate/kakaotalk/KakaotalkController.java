package com.bkmc.messengerconnector.communicate.kakaotalk;

import com.bkmc.messengerconnector.communicate.IController;
import com.bkmc.messengerconnector.config.Messenger;
import com.bkmc.messengerconnector.message.BasicMessage;
import com.bkmc.messengerconnector.message.Message;
import com.bkmc.messengerconnector.messagehub.InMessageHub;
import com.bkmc.messengerconnector.messagehub.messengerhub.KakaotalkMessageHub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    InMessageHub inMessageHub;

    @Autowired
    KakaotalkMessageHub kakaotalkMessageHub;

    @RequestMapping("/kakaotalk/receive")
    public String kakaotalkReceiver(HttpServletRequest request) {
        String channelFrom = request.getParameter("channel_from");
        String sender = request.getParameter("sender");
        String context = request.getParameter("context");

        Message msgDTO = new BasicMessage();
        msgDTO.setMessengerFrom(Messenger.KAKAOTALK.getValue());
        msgDTO.setChannelFrom(channelFrom);
        msgDTO.setSender(sender);
        msgDTO.setContext(context);

        inMessageHub.addMessage(msgDTO);
        System.out.println("Success inserting kakaotalk message to messagehub");
        inMessageHub.handlingMessage();

        return "Success";
    }

    @RequestMapping(value = "/kakaotalk/send", produces = "text/plain")
    public String getKakaotalkMessages() throws JsonProcessingException {
        Map output = new HashMap();
        ArrayList msgDTOs = kakaotalkMessageHub.pollAllMessage();
        ArrayList formattedMessages = mapFormattingProcess(msgDTOs);
        output.put("message_list", formattedMessages);
        return jsonStringFormattingProcess(output);
    }

    private ArrayList mapFormattingProcess(ArrayList msgDTOs) {
        ArrayList<HashMap> msgList = new ArrayList<>();

        for (Object msgDTO : msgDTOs) {
            HashMap<String, String> msg = new HashMap<>();

            Message message = (Message) msgDTO;
            msg.put("messenger_from", message.getMessengerFrom());
            msg.put("channel_from", message.getChannelFrom());
            msg.put("sender", message.getSender());
            msg.put("context", message.getContext());

            msgList.add(msg);
        }

        return msgList;
    }

    private String jsonStringFormattingProcess(Map messageMap) throws JsonProcessingException {
        String jsonString;

        ObjectMapper mapper = new ObjectMapper();
        jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageMap);

        return jsonString;
    }
}
