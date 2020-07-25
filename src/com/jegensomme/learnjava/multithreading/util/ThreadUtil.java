package com.iversatile.learningjava.multithreading.util;

import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadUtil {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void tryWait(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
