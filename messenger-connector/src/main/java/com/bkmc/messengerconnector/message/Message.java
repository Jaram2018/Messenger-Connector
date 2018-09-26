package com.bkmc.messengerconnector.message;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-11
 * Time: 오전 10:00
 */
public class Message extends Data {

    protected String channelFrom;
    protected String channelTo;
    protected String sender;
    protected String context;

    public Message() {
        this.channelFrom = "None";
        this.channelTo = "None";
        this.sender = "None";
        this.context = "None";
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

    @Override
    public String toString() {
        return "Message{" +
                "channelFrom='" + channelFrom + '\'' +
                ", channelTo='" + channelTo + '\'' +
                ", sender='" + sender + '\'' +
                ", context='" + context + '\'' +
                ", messengerFrom=" + messengerFrom + '\'' +
                ", messengerTo=" + messengerTo + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
