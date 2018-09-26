package com.bkmc.messengerconnector.communicate.slack;

import com.bkmc.messengerconnector.message.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-26
 * Time: 오후 2:45
 */
public class SlackBotController {

    private Message message;

    public SlackBotController(Message message) {
        this.message = message;
    }

    public Message getHandledMessage() {
        messageController();
        return message;
    }

    private void messageController() {
        String[] splitMessage = message.getContext().split("\\s");
        List<String> messageList = Arrays.asList(splitMessage);

        if (messageList.contains("비밀번호:")) {
            message.setContext("웹실 비밀번호: 40*9**19 *유출 조심!!!*");
        }
    }
}
