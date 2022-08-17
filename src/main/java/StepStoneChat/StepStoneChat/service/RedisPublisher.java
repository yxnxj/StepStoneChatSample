package StepStoneChat.StepStoneChat.service;

import StepStoneChat.StepStoneChat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

// import 생략...
@RequiredArgsConstructor
/**
 * 리스너 단일화 및 reditTemplate을 이용하여 기능 대체가 가능하므로 삭제
 */
//@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}