package com.bkmc.messengerconnector.communicate.slack;

import com.bkmc.messengerconnector.config.Settings;
import com.bkmc.messengerconnector.message.Message;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-16
 * Time: 오후 1:55
 */
@Service
public class SlackHttpCallProcess {

    private String oAuthToken = Settings.SLACK_OAUTH_TOKEN.getValue();
    private String webSocketUrl = Settings.SLACK_WEBHOOK_URL.getValue();

    public void sendMessageToSlack(Message message) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(webSocketUrl);

        String senderStr = String.format("%s [%s | %s]", message.getSender(), message.getMessengerFrom(), message.getChannelFrom());
        String textStr = message.getContext();
        /*
        String payload =
                "{\n" +
                "\t\"username\": \"" + senderStr + "\",\n" +
                "\t\"text\": \"" + textStr + "\"\n" +
                "}";
        */
        String payload =
                "{\n" +
                "\t\"text\": \"" + senderStr + "\n" + textStr + "\"\n" +
                "}";

        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
        request.setEntity(entity);

        httpClient.execute(request);
    }

    public String getUserInfo(String userId) throws IOException {
        String url = "https://slack.com/api/users.info?token=" + oAuthToken + "&user=" + userId;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(request);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent())
        );

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }

        bufferedReader.close();
        return response.toString();
    }
}
