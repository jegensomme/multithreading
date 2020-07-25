package com.iversatile.learningjava.multithreading.semaphores;

import com.iversatile.learningjava.multithreading.util.DefaultRunner;
import com.iversatile.learningjava.multithreading.util.ThreadTemplate;
import com.iversatile.learningjava.multithreading.util.concurrent.SemaphoreUtil;
import com.iversatile.learningjava.multithreading.util.ThreadUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MainThread {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);

        Thread thread1 = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                if(SemaphoreUtil.timeAcquireSemaphore(semaphore, 10, TimeUnit.SECONDS)) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print(1);
                        ThreadUtil.sleep(1000);
                    }
                    System.out.println();
                    semaphore.release();
                } else {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread-1");

        Thread thread2= new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                if(SemaphoreUtil.timeAcquireSemaphore(semaphore, 10, TimeUnit.SECONDS)) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print(2);
                        ThreadUtil.sleep(1000);
                    }
                    System.out.println();
                    semaphore.release();
                } else {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread-2");

        DefaultRunner.run(thread1, thread2);
    }
}
