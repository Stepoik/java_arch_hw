package org.example.hw3.server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sender extends Thread {
    private final CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();
    private final Set<SenderListener> listeners = ConcurrentHashMap.newKeySet();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                List<String> sendMessages = new ArrayList<>(messages);
                messages.clear();
                System.out.println("Messages: " + sendMessages);
                listeners.forEach((listener) -> listener.onMessages(sendMessages));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void addMessageListener(SenderListener listener) {
        listeners.add(listener);
    }

    public void removeMessageListener(SenderListener listener) {
        listeners.remove(listener);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
