package org.example.hw3.client;

import org.example.hw3.Constants;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (var socket = new Socket("127.0.0.1", Constants.SERVER_PORT)) {
            System.out.println("READY");
            var scanner = new Scanner(System.in);
            var out = new PrintWriter(socket.getOutputStream(), true);
            var messageReceiver = new MessageReceiver(socket);
            messageReceiver.start();
            while (scanner.hasNextLine()) {
                out.print(scanner.nextLine().trim());
                out.flush();
            }
        }
    }

    static class MessageReceiver extends Thread {
        private final DataInputStream in;
        private final Socket socket;

        MessageReceiver(Socket socket) throws IOException {
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (!socket.isClosed() && socket.isConnected()) {
                byte[] buffer = new byte[1024];
                try {
                    int length = in.read(buffer);
                    String messages = new String(buffer, 0, length);
                    if (!messages.isEmpty()) {
                        System.out.println(messages);
                    }
                } catch (IOException e) {
                    System.out.println("Socket closed");
                }
            }
        }
    }
}
