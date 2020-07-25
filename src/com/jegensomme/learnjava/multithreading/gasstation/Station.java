package com.iversatile.learningjava.multithreading.gasstation;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Station {

    public static final int INCOME_TIME = 2;
    public static final int STATION_NUMBER = 3;
    public static final List<GasStation> gasStations = new ArrayList<>();
    public static final Cashier cashier = new Cashier();
    public static final AtomicInteger CLIENT_COUNTER = new AtomicInteger(0);
    public static final Random RANDOM = new Random();

    static {
        for(int i = 0; i < STATION_NUMBER; i++) {
            gasStations.add(new GasStation());
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new CarIncomer(), 0, INCOME_TIME, TimeUnit.SECONDS);

        Scanner scanner = new Scanner(System.in);
        while(!"x".equals(scanner.next()));
        executorService.shutdown();
    }

    private static class CarIncomer implements Runnable {

        @Override
        public void run() {
            GasStation gasStation;
            List<GasStation> freeGasStations = gasStations.stream().
                                               filter(GasStation::isFree).
                                               collect(Collectors.toList());
            gasStation = freeGasStations.size() == 0 ?
                    gasStations.get(gasStations.size()) :
                    freeGasStations.get(RANDOM.nextInt(freeGasStations.size()));

            new Thread(new Car(gasStation, cashier),
                    "Car №" + CLIENT_COUNTER.incrementAndGet()).start();
        }
    }
}
