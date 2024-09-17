package org.example.hw3.server;

import org.example.hw3.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            sender.start();
            ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT);
            System.out.println("READY");
            while (true) {
                executorService.submit(new Receiver(serverSocket.accept(), sender));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            sender.interrupt();
            sender.join();
        }
    }
}
