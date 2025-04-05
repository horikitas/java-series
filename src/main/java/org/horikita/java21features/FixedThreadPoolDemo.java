package org.horikita.java21features;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    private static final int TASK_COUNT = 100_000;
    private static final int POOL_SIZE = 100; // Typical pool size

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Fixed Thread Pool Demo with " + TASK_COUNT + " tasks and pool size " + POOL_SIZE);

        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE)) {

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

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.println("Finished all tasks using fixed thread pool in " + duration.toMillis() + " ms");
    }
}
