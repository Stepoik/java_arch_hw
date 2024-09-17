package org.example.hw3.server;

import java.util.List;

@FunctionalInterface
public interface SenderListener {
    void onMessages(List<String> messages);
}
