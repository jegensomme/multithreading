package com.iversatile.learningjava.multithreading.util;

import java.util.Arrays;
import java.util.Scanner;

public class DefaultRunner {

    public static void run(Thread thread, Thread... threads) {
        thread.start();
        Arrays.stream(threads).forEach(thr -> {
            thr.start();
        });

        Scanner scanner = new Scanner(System.in);
        while(!"x".equals(scanner.next()));

        thread.interrupt();
        Arrays.stream(threads).forEach(thr -> {
            thr.interrupt();
        });
    }
}
