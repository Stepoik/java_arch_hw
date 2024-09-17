package org.example.hw3.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Receiver implements Runnable, SenderListener {
    private Sender sender;
    private Socket conn;

    public Receiver(Socket conn, Sender sender) {
        System.out.println("Connection established");
        this.sender = sender;
        this.conn = conn;
        sender.addMessageListener(this);
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(conn.getInputStream());
            byte[] buffer = new byte[1024];
            while (!conn.isClosed()) {
                int length = in.read(buffer);
                String message = new String(buffer, 0, length);
                if (!message.isEmpty()) {
                    System.out.println(message);
                    sender.addMessage(message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Connection closed");
            sender.removeMessageListener(this);
        }
    }

    @Override
    public void onMessages(List<String> messages) {
        try {
            var serverOutput = new PrintWriter(conn.getOutputStream());
            StringBuilder messagesStringBuilder = new StringBuilder();
            for (String message : messages) {
                messagesStringBuilder.append(message).append("\n");
            }
            if (!messagesStringBuilder.isEmpty()) {
                serverOutput.println(messagesStringBuilder);
                serverOutput.flush();
            }
        } catch (IOException e) {

        }
    }
}
