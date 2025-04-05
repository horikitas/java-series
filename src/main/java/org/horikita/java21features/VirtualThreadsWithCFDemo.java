package org.horikita.java21features;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirtualThreadsWithCFDemo {

    private static final int TASK_COUNT = 100_000;

    private final MyExternalApiClient apiClient;

    private final ExecutorService executorService;

    public VirtualThreadsWithCFDemo() {
        executorService = Executors.newVirtualThreadPerTaskExecutor();
        this.apiClient = new MyExternalApiClient(executorService);
    }

    public static void main(String[] args) throws Exception {
        VirtualThreadsWithCFDemo demo = new VirtualThreadsWithCFDemo();
        System.out.println("Starting Virtual Threads Demo with " + TASK_COUNT + " tasks...");

        Instant start = Instant.now();

        List<ResultPair> result = demo.submitTasksWithVirtualThreads();
        System.out.println("Results: " + result.size());

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Finished all tasks using virtual threads in " + duration.toMillis() + " ms");

        demo.shutDownHook();
    }

    private List<ResultPair> submitTasksWithVirtualThreads() throws InterruptedException, ExecutionException {
        List<Integer> requests = new ArrayList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            requests.add(i);
        }
        List<CompletableFuture<ResultPair>> completableFutures = requests.stream().map(apiClient::callAPIAsync).toList();

        return CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).thenApply(v -> {
            var resultPairs = completableFutures.stream()
                    .map(CompletableFuture::join).toList();
            System.out.println("All tasks completed");
            return resultPairs;
        }).get();
    }

    private void shutDownHook() throws InterruptedException {
        executorService.shutdown(); // Shutdown the executor service
        while (!executorService.isTerminated()) {
            Thread.sleep(100); // wait for termination
        }
    }

}

class MyExternalApiClient {

    ExecutorService executorService;

    public MyExternalApiClient(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public CompletableFuture<ResultPair> callAPIAsync(int requestId) {
        return CompletableFuture.supplyAsync(() -> externalAPICall(requestId), executorService);
    }

    private ResultPair externalAPICall(int requestId) {
        try {
            Thread.sleep(100); // Simulates blocking I/O (e.g., DB/API call)
            if (requestId % 20_000 == 0) {
                System.out.println("Completed task #" + requestId + " on " + Thread.currentThread());
            }
            return new ResultPair(requestId, "SUCCESS: " + requestId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ResultPair(requestId,  "ERROR: " + requestId);
        }
    }
}

record ResultPair(int requestId, String result) {}

