package org.example.hw4;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessageHandler extends TextWebSocketHandler {


    
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

        for (String message : messages) {
            session.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        messages.add(message.getPayload());

        for (WebSocketSession activeSession : sessions) {
            if (activeSession.isOpen()) {
                activeSession.sendMessage(new TextMessage(message.getPayload()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
