package com.jegensomme.multithreading.gasstation;

import com.jegensomme.multithreading.util.concurrent.SemaphoreUtil;
import com.jegensomme.multithreading.util.ThreadUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Cashier {

    public static final int SERVE_TIME = 2;
    private final Semaphore semaphore = new Semaphore(1);

    public Cashier() {
    }

    public void serve() {
        SemaphoreUtil.acquireSemaphore(semaphore);
        System.out.println("Serving " + Thread.currentThread().getName());
        ThreadUtil.sleep(TimeUnit.SECONDS.toMillis(SERVE_TIME));
        System.out.println("Served " + Thread.currentThread().getName());
        semaphore.release();
    }
}
