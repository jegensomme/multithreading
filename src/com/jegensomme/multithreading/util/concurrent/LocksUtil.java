package com.jegensomme.multithreading.util.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LocksUtil {

    public static void wrapInLocker(ReentrantLock locker, Runnable runnable) {
        try {
            locker.lockInterruptibly();
            runnable.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (locker.isLocked()) {
                locker.unlock();
            }
        }
    }

    public static void tryAwait(Condition condition) {
        try {
            condition.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void tryWait(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
