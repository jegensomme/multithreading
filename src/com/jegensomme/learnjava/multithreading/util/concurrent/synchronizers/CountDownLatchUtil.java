package com.iversatile.learningjava.multithreading.util.concurrent.synchronizers;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUtil {

    public static void await(CountDownLatch starter, String messageAfterInterrupting) {
        try {
            starter.await();
        } catch (InterruptedException e) {
            System.out.println(messageAfterInterrupting);
            Thread.currentThread().interrupt();
        }
    }
}
