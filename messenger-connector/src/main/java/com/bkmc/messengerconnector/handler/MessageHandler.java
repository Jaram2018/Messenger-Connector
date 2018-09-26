package com.bkmc.messengerconnector.handler;

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
 * Time: 오후 5:28
 */
@Service
public class MessageHandler {

    @Autowired
    FunctionController functionController;

    private Queue<Message> outMessageQueue = new ArrayDeque<>();

    public Queue<Message> execute(Queue<Message> inMessageQueue) {
        while(!inMessageQueue.isEmpty()) {
            Message handledMessage;

            Message message = inMessageQueue.poll();
            String context = message.getContext();

            CommandAndContext cAndc = getCommandAndContext(context);
            functionController.setCommand(cAndc.getCommand());
            functionController.setContextArray(cAndc.getContextArray());
            functionController.setMessage(message);

            handledMessage = functionController.getHandledMessage();

            outMessageQueue.add(handledMessage);
        }

        return outMessageQueue;
    }

    /**
     * Return the instance 'CommandAndContext' that has parameters of command and contextArray
     * @param context
     * @return char command, String[] contextArray
     */
    private CommandAndContext getCommandAndContext(String context) {
        Character command;
        String[] splitText = context.split("\\s");
        String[] splitFirstWord = splitText[0].split("");

        StringBuilder builder = new StringBuilder();
        for (int i=1; i<splitFirstWord.length; i++) {
            builder.append(splitFirstWord[i]);
        }

        command = splitFirstWord[0].charAt(0);
        splitText[0] = builder.toString();

        return new CommandAndContext(command, splitText);
    }

    /**
     * Data instance of 'command' and 'context'
     */
    public static class CommandAndContext {

        private Character command;
        private String[] contextArray;

        public CommandAndContext(Character command, String[] contextArray) {
            this.command = command;
            this.contextArray = contextArray;
        }

        public Character getCommand() {
            return command;
        }

        public String[] getContextArray() {
            return contextArray;
        }
    }
}
