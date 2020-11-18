package com.jegensomme.multithreading.exchanging;

import com.jegensomme.multithreading.util.ThreadTemplate;
import com.jegensomme.multithreading.util.ThreadUtil;
import com.jegensomme.multithreading.util.concurrent.ExchangerUtil;

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
