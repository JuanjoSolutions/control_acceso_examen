package com.examen.control_acceso_examen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class ControlAccesoParqueApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ControlAccesoParqueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Configuración del monitor de puertas: número de puertas disponibles
        int numberOfGates = 3;
        EntranceMonitor entranceMonitor = new EntranceMonitor(numberOfGates);

        // Configuración de monitores para las atracciones:
        // Por ejemplo, Atracción 1 con capacidad para 4 visitantes y Atracción 2 para 2 visitantes.
        AttractionMonitor attraction1Monitor = new AttractionMonitor(4);
        AttractionMonitor attraction2Monitor = new AttractionMonitor(2);

        // Generador de códigos únicos para visitantes
        AtomicInteger codeGenerator = new AtomicInteger(1);

        // Creación de un pool de hilos para simular la concurrencia de visitantes
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Número total de visitantes a simular
        int numberOfVisitors = 10;
        for (int i = 0; i < numberOfVisitors; i++) {
            int visitorCode = codeGenerator.getAndIncrement();
            Visitor visitor = new Visitor(visitorCode, entranceMonitor, attraction1Monitor, attraction2Monitor);
            executorService.submit(visitor);
            // Simula la llegada aleatoria de visitantes (entre 0 y 1 segundo)
            Thread.sleep((long) (Math.random() * 1000));
        }

        // Finalizar la ejecución del pool de hilos
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("Simulación finalizada.");
    }
}
