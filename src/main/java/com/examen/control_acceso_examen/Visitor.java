package com.examen.control_acceso_examen;

public class Visitor implements Runnable {
    private final int code;
    private final EntranceMonitor entranceMonitor;
    private final AttractionMonitor attraction1Monitor;
    private final AttractionMonitor attraction2Monitor;

    public Visitor(int code, EntranceMonitor entranceMonitor,
                   AttractionMonitor attraction1Monitor, AttractionMonitor attraction2Monitor) {
        this.code = code;
        this.entranceMonitor = entranceMonitor;
        this.attraction1Monitor = attraction1Monitor;
        this.attraction2Monitor = attraction2Monitor;
    }

    @Override
    public void run() {
        try {
            System.out.println("Visitante " + code + " llega al parque.");

            // Acceso a las puertas de entrada mediante monitor
            entranceMonitor.acquireGate();
            System.out.println("Visitante " + code + " está entrando al parque.");
            Thread.sleep(500);  // Simula el tiempo de ingreso
            entranceMonitor.releaseGate();
            System.out.println("Visitante " + code + " ha ingresado al parque.");

            // Acceso a la Atracción 1
            System.out.println("Visitante " + code + " espera para la Atracción 1.");
            attraction1Monitor.enter();
            System.out.println("Visitante " + code + " accede a la Atracción 1.");
            Thread.sleep(1000);  // Simula el tiempo en la atracción
            attraction1Monitor.exit();
            System.out.println("Visitante " + code + " sale de la Atracción 1.");

            // Acceso a la Atracción 2
            System.out.println("Visitante " + code + " espera para la Atracción 2.");
            attraction2Monitor.enter();
            System.out.println("Visitante " + code + " accede a la Atracción 2.");
            Thread.sleep(800);  // Simula el tiempo en la atracción
            attraction2Monitor.exit();
            System.out.println("Visitante " + code + " sale de la Atracción 2.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Visitante " + code + " fue interrumpido.");
        }
    }
}
