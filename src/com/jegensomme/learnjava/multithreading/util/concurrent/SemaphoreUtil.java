package com.iversatile.learningjava.multithreading.util.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreUtil {

    public static boolean acquireSemaphore(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    public static boolean timeAcquireSemaphore(Semaphore semaphore, long timeOut, TimeUnit timeUnit) {
        try {
            return semaphore.tryAcquire(timeOut, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
