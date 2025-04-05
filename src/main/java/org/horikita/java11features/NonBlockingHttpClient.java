package org.horikita.java11features;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Non-blocking HTTP client out of the box.
public class NonBlockingHttpClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Create a new HttpClient
        var client = java.net.http.HttpClient.newHttpClient();

        // Create a new HttpRequest
        var request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create("https://api.github.com"))
                .build();

        // Send the request asynchronously
        client.sendAsync(request, java.net.http.HttpResponse.BodyHandlers.ofString())
                .thenApply(java.net.http.HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
