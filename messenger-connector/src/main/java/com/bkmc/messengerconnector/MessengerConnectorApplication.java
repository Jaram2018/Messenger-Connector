package com.bkmc.messengerconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerConnectorApplication {

    public static void main(String[] args) {
        // SpringApplication.run(MessengerConnectorApplication.class, args);
        SpringApplication app = new SpringApplication(MessengerConnectorApplication.class);

        app.run(args);
    }
}
