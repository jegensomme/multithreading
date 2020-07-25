package com.iversatile.learningjava.multithreading.gasstation;

import com.iversatile.learningjava.multithreading.util.concurrent.SemaphoreUtil;
import com.iversatile.learningjava.multithreading.util.ThreadUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class GasStation {

    public static final int SERVE_TIME = 2;
    private final Semaphore semaphore = new Semaphore(1);
    private boolean free = true;

    public GasStation() {
    }

    public void serve() {
        SemaphoreUtil.acquireSemaphore(semaphore);
        System.out.println("Start filling " + Thread.currentThread().getName());
        free = false;
        ThreadUtil.sleep(TimeUnit.SECONDS.toMillis(SERVE_TIME));
        free = true;
        System.out.println("End filling " + Thread.currentThread().getName());
        semaphore.release();
    }

    public boolean isFree() {
        return free;
    }
}
