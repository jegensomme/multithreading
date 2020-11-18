package com.jegensomme.multithreading.util;

public abstract class ThreadTemplate implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is started");
        while(!Thread.currentThread().isInterrupted()) {
            action();
        }
        System.out.println(Thread.currentThread().getName() + " is interrupted");
    }

    public abstract void action();
}
