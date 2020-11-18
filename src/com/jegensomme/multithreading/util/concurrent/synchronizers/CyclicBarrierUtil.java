package com.jegensomme.multithreading.util.concurrent.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUtil {

    public static void await(CyclicBarrier barrier, String messageAfterInterrupting) {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(messageAfterInterrupting);
            Thread.currentThread().interrupt();
        }
    }
}
