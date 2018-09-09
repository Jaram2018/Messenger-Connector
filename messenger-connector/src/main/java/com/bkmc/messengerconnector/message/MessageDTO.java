package com.bkmc.messengerconnector.message;

import com.bkmc.messengerconnector.config.Messenger;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project: messengers-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 5:15
 */
public class MessageDTO {

    private Messenger messenger;
    private String channel;
    private String sender;
    private String context;
    private Date date;

    public MessageDTO() {
        this.messenger = Messenger.NONE;
        this.channel = "None";
        this.sender = "None";
        this.context = "None";
        this.date = new Date();
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messenger=" + messenger +
                ", channel='" + channel + '\'' +
                ", sender='" + sender + '\'' +
                ", context='" + context + '\'' +
                ", date=" + date +
                '}';
    }
}
