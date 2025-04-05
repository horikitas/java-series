package org.horikita.java21features;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Structured Concurrency Demo...");

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Subtask<String> userSubTask = scope.fork(() -> fetchUser());
            Subtask<String> orderSubTask = scope.fork(() -> fetchOrder());

            scope.joinUntil(Instant.now().plusMillis(Duration.ofMillis(1000).toMillis()));              // Wait for all
            scope.throwIfFailed();    // Propagate exceptions if any

            String user = userSubTask.get();
            String order = orderSubTask.get();

            System.out.println("User: " + user);
            System.out.println("Order: " + order);
        }

        System.out.println("Structured Concurrency flow complete.");
    }

    static String fetchUser() throws InterruptedException {
        Thread.sleep(500); // Simulate I/O delay
        return "Uchiha Sasuke";
    }

    static String fetchOrder() throws InterruptedException {
        Thread.sleep(800); // Simulate I/O delay
        return "Katana Order #928";
    }
}
