package com.jegensomme.multithreading;

import java.util.Scanner;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            thread1.start();
            while (!Thread.currentThread().isInterrupted());
            thread1.interrupt();
        }, "Thread 2");

        thread2.start();
        Scanner scanner = new Scanner(System.in);
        while(!"x".equals(scanner.nextLine()));
        thread2.interrupt();
    }
}
