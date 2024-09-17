package org.example.hw1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();
        Condition condition = locker.newCondition();
        Thread pingThread = new Thread(() -> {
            locker.lock();
            try {
                while (true) {
                    System.out.println("Ping");
                    Thread.sleep(1000);
                    condition.signal();
                    condition.await();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            } finally {
                locker.unlock();
            }
        });
        Thread pongThread = new Thread(() -> {
            locker.lock();
            try {
                while (true) {
                    condition.await();
                    System.out.println("Pong");
                    Thread.sleep(1000);
                    condition.signal();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            } finally {
                locker.unlock();
            }

        });
        pongThread.start();
        Thread.sleep(100);
        pingThread.start();
        pingThread.join();
        pongThread.join();
    }
}