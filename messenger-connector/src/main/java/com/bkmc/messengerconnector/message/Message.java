package com.bkmc.messengerconnector.message;

import com.bkmc.messengerconnector.config.Messenger;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-11
 * Time: 오전 10:00
 */
public abstract class Message {

    private Messenger messenger;
    private String channelFrom;
    private String channelTo;
    private String sender;
    private String context;
    private Date date;

    public Message() {
        this.messenger = Messenger.NONE;
        this.channelFrom = "None";
        this.channelTo = "None";
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

    public String getChannelFrom() {
        return channelFrom;
    }

    public void setChannelFrom(String channelFrom) {
        this.channelFrom = channelFrom;
    }

    public String getChannelTo() {
        return channelTo;
    }

    public void setChannelTo(String channelTo) {
        this.channelTo = channelTo;
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
        return "Message{" +
                "messenger=" + messenger +
                ", channelFrom='" + channelFrom + '\'' +
                ", channelTo='" + channelTo + '\'' +
                ", sender='" + sender + '\'' +
                ", context='" + context + '\'' +
                ", date=" + date +
                '}';
    }
}
