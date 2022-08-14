package StepStoneChat.StepStoneChat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
//@RequiredArgsConstructor
//@EnableWebSocket // WebSocket 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    private final WebSocketHandler webSocketHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //메시지를 구독하는 요청의 prefix는 /sub 로 시작하도록 설정
        registry.enableSimpleBroker("/sub");
        //메시지를 발행하는 요청의 prefix는 /pub 로 시작하도록 설정
        registry.setApplicationDestinationPrefixes("/pub");
//        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/chat");
//        registry.addEndpoint("/chat").withSockJS();
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*")
                .withSockJS();
        //stomp websocket 연결 endpoint는 /ws-stomp로 설정
        //개발 서버 접속 주소는 ws://localhost:8080/ws-stomp 다.
    }

    /**
     * WebSocket 만으로 구현하면 각 채팅방과 세션을 일일이 구현하고 메시지 발송 부분을 관리하는 추가 코드를 구현해 줘야 한다.
     * broker를 사용하여 pub/sub으로 최적화 된 방식으로 메시징을 구현한다.
     */

//    /**
//     * addHandler : WebSocket에 접속하기 위한 endPoint를 /ws/chat/으로 설정한다.
//     * setAllowedOrigins : CORS, 도메인이 다른 서버에서도 접속 가능하도록 설정
//     * @param registry
//     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws/chats").setAllowedOrigins("*");
//    }
}
