package com.jegensomme.multithreading.threads;

import com.jegensomme.multithreading.util.DefaultRunner;
import com.jegensomme.multithreading.util.ThreadTemplate;
import com.jegensomme.multithreading.util.ThreadUtil;

public class MainThread {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                System.out.println(2);
                ThreadUtil.sleep(2000);
            }
        }, "Thread-1");

        Thread thread2 = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                System.out.println(1);
                ThreadUtil.sleep(2000);
            }
        }, "Thread-2");

        DefaultRunner.run(thread1, thread2);
    }
}
