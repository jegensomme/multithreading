package com.iversatile.learningjava.multithreading.synchronizing.buffer;

import com.iversatile.learningjava.multithreading.util.DefaultRunner;
import com.iversatile.learningjava.multithreading.util.ThreadTemplate;
import com.iversatile.learningjava.multithreading.util.ThreadUtil;

public class MainThread {

    public static void main(String[] args) {

        final String MESSAGE = "Hello terrible wold!";
        Buffer buffer = new BufferLockerImpl(MESSAGE.length());

        Thread writer = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                for(int i = 0; i < MESSAGE.length(); i++) {
                    if(Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    buffer.write(MESSAGE.charAt(i));
                    ThreadUtil.sleep(500);
                }
            }
        }, "Writer");

        Thread reader = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                buffer.read();
                ThreadUtil.sleep(500);
            }
        }, "Reader");

        DefaultRunner.run(writer, reader);
    }
}
