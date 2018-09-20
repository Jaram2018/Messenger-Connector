package com.bkmc.messengerconnector.config;

import com.bkmc.messengerconnector.messagehub.messengerhub.KakaotalkMessageHub;
import com.bkmc.messengerconnector.messagehub.OutMessageHub;
import com.bkmc.messengerconnector.messagehub.messengerhub.SlackMessageHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-09
 * Time: 오후 7:12
 */
@Component
public class AppliactionStartedHandler implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    OutMessageHub outMessageHub;

    @Autowired
    KakaotalkMessageHub kakaotalkMessageHub;

    @Autowired
    SlackMessageHub slackMessageHub;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        /**
         * @TODO changing System.out to Log
         */
        System.out.println("Putting Message-Hubs ...");
        outMessageHub.hubMapPutElement(Messenger.KAKAOTALK.getValue(), kakaotalkMessageHub);
        System.out.println("Succeed putting 'Kakaotalk' Message-Hub");
        outMessageHub.hubMapPutElement(Messenger.SLACK.getValue(), slackMessageHub);
        System.out.println("Succeed putting 'Slack' Message-Hub");
        // adding other communications ...
    }
}
