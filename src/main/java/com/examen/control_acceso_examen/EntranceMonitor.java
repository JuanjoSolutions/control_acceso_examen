package com.examen.control_acceso_examen;

public class EntranceMonitor {
    private int availableGates;

    public EntranceMonitor(int availableGates) {
        this.availableGates = availableGates;
    }

    public synchronized void acquireGate() throws InterruptedException {
        while (availableGates <= 0) {
            wait();
        }
        availableGates--;
    }

    public synchronized void releaseGate() {
        availableGates++;
        notifyAll();
    }
}
