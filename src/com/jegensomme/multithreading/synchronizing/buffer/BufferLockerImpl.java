package com.jegensomme.multithreading.synchronizing.buffer;

import com.jegensomme.multithreading.util.concurrent.LocksUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLockerImpl extends Buffer {

    private ReentrantLock locker;
    private Condition condition;

    public BufferLockerImpl(int size) {
        super(size);
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    @Override
    public void write(char ch) {
        LocksUtil.wrapInLocker(locker, () -> {
            while(n == buffer.length && !Thread.currentThread().isInterrupted()) {
                LocksUtil.tryAwait(condition);
            }
            if(!Thread.currentThread().isInterrupted()) {
                buffer[in] = ch;
                in = (in + 1) % buffer.length;
                n++;
            }
            condition.signal();
        });
    }

    @Override
    public void read() {
        LocksUtil.wrapInLocker(locker, () -> {
            while(n == 0 && !Thread.currentThread().isInterrupted()) {
                LocksUtil.tryAwait(condition);
            }
            if(!Thread.currentThread().isInterrupted()) {
                System.out.print(buffer[out]);
                out = (out + 1) % buffer.length;
                n--;
            }
            condition.signal();
        });
    }
}
