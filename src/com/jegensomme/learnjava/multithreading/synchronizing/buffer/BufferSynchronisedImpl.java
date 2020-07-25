package com.iversatile.learningjava.multithreading.synchronizing.buffer;

import com.iversatile.learningjava.multithreading.util.ThreadUtil;

public class BufferSynchronisedImpl extends Buffer {

    public BufferSynchronisedImpl(int size) {
        super(size);
    }

    public synchronized void write(char ch) {
        while(n == buffer.length) {
            ThreadUtil.tryWait(this);
        }
        if(!Thread.currentThread().isInterrupted()) {
            buffer[in] = ch;
            in = (in + 1) % buffer.length;
            n++;
        }
        notify();
    }

    @Override
    public synchronized void read() {
        while(n == 0) {
            ThreadUtil.tryWait(this);
        }
        if(!Thread.currentThread().isInterrupted()) {
            System.out.print(buffer[out]);
            out = (out + 1) % buffer.length;
            n--;
        }
        notify();
    }
}
