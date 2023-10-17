package com.finracy.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagePublisherImpl implements MessagePublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;
    @Override
    public void publish(String message) {

    }
}
