package com.jegensomme.multithreading.synchronizing.synchronizers.cyclicbarrier;

import com.jegensomme.multithreading.util.DefaultRunner;
import com.jegensomme.multithreading.util.ThreadTemplate;
import com.jegensomme.multithreading.util.ThreadUtil;
import com.jegensomme.multithreading.util.concurrent.synchronizers.CountDownLatchUtil;
import com.jegensomme.multithreading.util.concurrent.synchronizers.CyclicBarrierUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Train {

    public static final AtomicInteger counter = new AtomicInteger(0);
    public static final int MIN_PASSENGERS = 5;
    public static CountDownLatch START_BOARDING = new CountDownLatch(1);
    public static final CyclicBarrier BOARDING = new CyclicBarrier(MIN_PASSENGERS, new TrainRunner());
    public static List<Thread> passengers = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        Thread workCycle = new Thread(new ThreadTemplate() {
            @Override
            public void action() {
                START_BOARDING = new CountDownLatch(1);
                passengers.clear();
                for(int i = 0; i < MIN_PASSENGERS; i++) {
                    Thread passenger = new Thread(new Passenger());
                    passenger.start();
                    passengers.add(passenger);
                    ThreadUtil.sleep(2000);
                    if(Thread.currentThread().isInterrupted()) {
                        interrupt();
                        break;
                    }
                }
                CountDownLatchUtil.await(START_BOARDING, "Work is ended");
            }
        }, "Train");
        DefaultRunner.run(workCycle);
    }

    public static void interrupt() {
        passengers.forEach(p -> {
            p.interrupt();
        });
    }

    public static class TrainRunner implements Runnable {
        @Override
        public void run() {
            System.out.println("The train started moving");
            ThreadUtil.sleep(5000);
            System.out.println("The train arrived");
            START_BOARDING.countDown();
        }
    }

    public static class Passenger implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().setName("Passenger №" + counter.incrementAndGet());
            System.out.println("Entered " + Thread.currentThread().getName());
            CyclicBarrierUtil.await(BOARDING, Thread.currentThread().getName() + " left the train");
        }
    }
}
