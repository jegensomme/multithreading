package com.jegensomme.multithreading.synchronizing.synchronizers.countdownlatch;

import com.jegensomme.multithreading.util.ThreadUtil;
import com.jegensomme.multithreading.util.concurrent.synchronizers.CountDownLatchUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitingRoom {

    public static final int NUMBER = 5;
    public static AtomicInteger counter = new AtomicInteger(0);
    public static final CountDownLatch STARTER = new CountDownLatch(NUMBER + 1);

    public static void main(String[] args) {
        System.out.println("Waiting until minimum 5 players enter the room");
        for(int i = 0; i < 5; i++) {
            new Thread(new Player()).start();
            System.out.println(STARTER.getCount() - 1 + " players left to start");
            ThreadUtil.sleep(2000);
        }
        new Thread(() -> {
            System.out.println("Loading game...");
            for(int i = 0; i < 5; i++) {
                System.out.println(5 - i);
                ThreadUtil.sleep(1000);
            }
            STARTER.countDown();
            System.out.println("The game started!");
        }).start();
    }

    public static class Player implements Runnable {

        @Override
        public void run() {
            Thread.currentThread().setName("Player №" + counter.incrementAndGet());
            System.out.println(Thread.currentThread().getName() + " entered the room");
            STARTER.countDown();
            CountDownLatchUtil.await(STARTER, Thread.currentThread().getName() + " disconnected");
            System.out.println(Thread.currentThread().getName() + " ready to play");
        }
    }
}
