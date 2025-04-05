package org.horikita.java21features;

import java.util.concurrent.*;

public class StructuredConcurrencyJava17Backport {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2); // or custom pool

    public static void main(String[] args) throws Exception {
        Future<String> userFuture = null;
        Future<String> orderFuture = null;

        try {
            userFuture = executor.submit(() -> fetchUser());
            orderFuture = executor.submit(() -> fetchOrder());

            String user = userFuture.get();   // Wait for user task
            String order = orderFuture.get(); // Wait for order task

            System.out.println("User: " + user);
            System.out.println("Order: " + order);

        } catch (Exception e) {
            System.err.println("Exception during task execution: " + e.getMessage());
            // Cancel both if any failed
            if (userFuture != null) userFuture.cancel(true);
            if (orderFuture != null) orderFuture.cancel(true);
        } finally {
            executor.shutdownNow(); // optional, or reuse elsewhere
        }
    }

    static String fetchUser() throws InterruptedException {
        Thread.sleep(100); // Simulate I/O
        return "Sasuke Uchiha";
    }

    static String fetchOrder() throws InterruptedException {
        Thread.sleep(100); // Simulate I/O
        return "Shuriken Order #928";
    }
}
