package com.jegensomme.multithreading.synchronizing.buffer;

public abstract class Buffer {

    protected char[] buffer;
    protected int in = 0;
    protected int out = 0;
    protected int n = 0;

    public Buffer(int size) {
        buffer = new char[size];
    }

    public abstract void write(char ch);

    public abstract void read();
}
