package com.bkmc.messengerconnector.message;

import com.bkmc.messengerconnector.config.Messenger;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-16
 * Time: 오후 2:32
 */
public class Data {

    protected String messengerFrom;
    protected ArrayList<String> messengerTo;
    protected String jsonData;
    protected Date date;

    public Data() {
        this.messengerFrom = Messenger.NONE.getValue();
        this.messengerTo = new ArrayList<>();
        this.jsonData = "{}";
        this.date = new Date();
    }

    public String getMessengerFrom() {
        return messengerFrom;
    }

    public void setMessengerFrom(String messengerFrom) {
        this.messengerFrom = messengerFrom;
    }

    public ArrayList<String> getMessengerTo() {
        return messengerTo;
    }

    public void setMessengerTo(ArrayList<String> messengerTo) {
        this.messengerTo = messengerTo;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "messengerFrom=" + messengerFrom +
                ", jsonData='" + jsonData + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
