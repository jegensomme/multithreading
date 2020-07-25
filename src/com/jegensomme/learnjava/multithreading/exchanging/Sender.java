package com.iversatile.learningjava.multithreading.exchanging;

import com.iversatile.learningjava.multithreading.util.ThreadTemplate;
import com.iversatile.learningjava.multithreading.util.ThreadUtil;
import com.iversatile.learningjava.multithreading.util.concurrent.ExchangerUtil;

import java.util.concurrent.Exchanger;

public class Sender extends ThreadTemplate {

    private Exchanger<String> exchanger;
    private String message = "message №";
    int counter = 1;

    public Sender(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void action() {
        if(ExchangerUtil.send(exchanger, message + counter)) {
            System.out.println(Thread.currentThread().getName() + " sent " + message + counter++);
        }
        ThreadUtil.sleep(2000);
    }
}
