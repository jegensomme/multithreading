package com.iversatile.learningjava.multithreading.gasstation;

public class Car implements Runnable {

    private final GasStation gasStation;
    private final Cashier cashier;

    public Car(GasStation gasStation, Cashier cashier) {
        this.gasStation = gasStation;
        this.cashier = cashier;
    }

    @Override
    public void run() {
        System.out.println("arrived " + Thread.currentThread().getName());
        takeGasStation();
        goToCashier();
        System.out.println("Thank you!");
    }

    private void takeGasStation() {
        gasStation.serve();
    }

    private void goToCashier() {
        cashier.serve();
    }
}
