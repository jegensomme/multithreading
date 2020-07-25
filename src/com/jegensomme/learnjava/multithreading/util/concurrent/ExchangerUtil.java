package com.iversatile.learningjava.multithreading.util.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerUtil {

    public static <T> boolean send(Exchanger<T> exchanger, T message) {
        try {
            exchanger.exchange(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T> T receive(Exchanger<T> exchanger) {
        try {
            return exchanger.exchange(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
