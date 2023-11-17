package com.pastebin.authentication.amqp;

import com.pastebin.authentication.dto.RegisterNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterNotificationProducer {
    private final AmqpTemplate amqpTemplate;

    public void publishEmail(RegisterNotificationDTO notificationDTO,
                             String exchange, String routingKey) {
        amqpTemplate.convertAndSend(exchange, routingKey, notificationDTO);
    }
}
