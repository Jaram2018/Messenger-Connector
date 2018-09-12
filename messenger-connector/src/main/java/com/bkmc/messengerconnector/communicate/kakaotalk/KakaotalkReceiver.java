package com.bkmc.messengerconnector.communicate.kakaotalk;

import com.bkmc.messengerconnector.message.BasicMessage;
import com.bkmc.messengerconnector.message.Message;
import com.bkmc.messengerconnector.messagehub.EntireMessageHub;
import com.bkmc.messengerconnector.config.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 5:25
 */
@RestController
public class KakaotalkReceiver {

    @Autowired
    EntireMessageHub entireMessageHub;

    @RequestMapping("/kakaotalkReceiver")
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
}
