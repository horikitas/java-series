package org.horikita.java21features;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadsDemoBasic {

    private static final int TASK_COUNT = 100_000;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Virtual Threads Demo with " + TASK_COUNT + " tasks...");

        Instant start = Instant.now();


        // Java 21 virtual thread executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < TASK_COUNT; i++) {
                int taskId = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(100); // Simulates blocking I/O
                        if (taskId % 20_000 == 0) {
                            System.out.println("Completed task #" + taskId + " on " + Thread.currentThread());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.sleep(100); // wait for termination
            }
        }
        /*List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            int taskId = i;
            Thread vt = Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(100); // Simulates blocking I/O
                    if (taskId % 20_000 == 0) {
                        System.out.println("Completed task #" + taskId + " on " + Thread.currentThread());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(vt);
        }

        // Join all threads to ensure they finish before exiting
        for (Thread vt : threads) {
            vt.join();
        }*/


        /**/

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.println("Finished all tasks using virtual threads in " + duration.toMillis() + " ms");
    }
}