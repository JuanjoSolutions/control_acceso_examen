package com.examen.control_acceso_examen;

public class AttractionMonitor {
    private int availableSpots;

    public AttractionMonitor(int capacity) {
        this.availableSpots = capacity;
    }

    public synchronized void enter() throws InterruptedException {
        while (availableSpots <= 0) {
            wait();
        }
        availableSpots--;
    }

    public synchronized void exit() {
        availableSpots++;
        notifyAll();
    }
}
