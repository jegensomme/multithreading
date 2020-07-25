package com.iversatile.learningjava.multithreading.exchanging;

import com.iversatile.learningjava.multithreading.util.ThreadTemplate;
import com.iversatile.learningjava.multithreading.util.concurrent.ExchangerUtil;

import java.util.concurrent.Exchanger;

public class Receiver extends ThreadTemplate {

    private Exchanger<String > exchanger;
    private String receivedMessage;

    public Receiver(Exchanger<String > exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void action() {
        receivedMessage = ExchangerUtil.receive(exchanger);
        if(receivedMessage != null) {
            System.out.println(Thread.currentThread().getName() + " received " + receivedMessage);
        }
    }
}
