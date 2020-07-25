package com.iversatile.learningjava.multithreading.exchanging;

import com.iversatile.learningjava.multithreading.util.DefaultRunner;

import java.util.concurrent.Exchanger;

public class MainThread {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();
        Thread sender = new Thread(new Sender(exchanger),"Sender");
        Thread receiver = new Thread(new Receiver(exchanger),"Receiver");

        DefaultRunner.run(sender, receiver);
    }
}
