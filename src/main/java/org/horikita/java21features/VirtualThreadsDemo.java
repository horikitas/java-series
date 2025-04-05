package org.horikita.java21features;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirtualThreadsDemo {

    private static final int TASK_COUNT = 100_000;

    private final ExecutorService executorService;
    private final MyExternalApiClient2 apiClient;

    public VirtualThreadsDemo() {
        executorService = Executors.newVirtualThreadPerTaskExecutor();
        this.apiClient = new MyExternalApiClient2(executorService);
    }

    public static void main(String[] args) throws Exception {
        VirtualThreadsDemo demo = new VirtualThreadsDemo();
        System.out.println("Starting Virtual Threads Demo with " + TASK_COUNT + " tasks...");

        Instant start = Instant.now();

        List<ResultPair> result = demo.submitTasksWithVirtualThreads();
        System.out.println("Results: " + result.size());

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Finished all tasks using virtual threads in " + duration.toMillis() + " ms");
    }

    private List<ResultPair> submitTasksWithVirtualThreads() throws InterruptedException, ExecutionException {
        List<Integer> requests = new ArrayList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            requests.add(i);
        }

        // Submit using plain submit() without CompletableFuture
        List<Future<ResultPair>> futures = new ArrayList<>(TASK_COUNT);
        for (int requestId : requests) {
            futures.add(apiClient.callAPIWithSubmit(requestId));
        }

        // Collect results using Future.get()
        List<ResultPair> results = new ArrayList<>(TASK_COUNT);
        for (Future<ResultPair> future : futures) {
            results.add(future.get());
        }

        executorService.shutdown(); // Shutdown the executor service
        while (!executorService.isTerminated()) {
            Thread.sleep(100); // wait for termination
        }

        System.out.println("All tasks completed");
        return results;
    }
}

class MyExternalApiClient2 {

    private final ExecutorService executorService;

    public MyExternalApiClient2(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<ResultPair> callAPIWithSubmit(int requestId) {
        return executorService.submit(() -> externalAPICall(requestId));
    }

    private ResultPair externalAPICall(int requestId) {
        try {
            Thread.sleep(100); // Simulates blocking I/O
            if (requestId % 20_000 == 0) {
                System.out.println("Completed task #" + requestId + " on " + Thread.currentThread());
            }
            return new ResultPair(requestId, "SUCCESS: " + requestId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ResultPair(requestId, "ERROR: " + requestId);
        }
    }
}

