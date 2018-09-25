package com.bkmc.messengerconnector.handler;

import com.bkmc.messengerconnector.config.Command;
import com.bkmc.messengerconnector.handler.function.ApplicationFunction;
import com.bkmc.messengerconnector.handler.function.Function;
import com.bkmc.messengerconnector.handler.function.NoneFunction;
import com.bkmc.messengerconnector.handler.function.SlackFunction;
import com.bkmc.messengerconnector.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-17
 * Time: 오후 5:59
 */
@Service
public class FunctionController {

    private Character command;
    private String[] contextArray;
    private Message message;

    public Message getHandledMessage() {
        Function function;

        if (command.equals(Command.ANNOTATION.getValue())) {
            function = new ApplicationFunction();
            function.setContextArray(contextArray);
            function.setMessage(message);
        } else if (command.equals(Command.EXCLAMATION.getValue())) {
            function = new SlackFunction();
            function.setContextArray(contextArray);
            function.setMessage(message);
        } else {
            function = new NoneFunction();
            function.setContextArray(contextArray);
            function.setMessage(message);
        }

        return function.getHandledMessage(contextArray);
    }

    public void setCommand(Character command) {
        this.command = command;
    }

    public void setContextArray(String[] contextArray) {
        this.contextArray = contextArray;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
