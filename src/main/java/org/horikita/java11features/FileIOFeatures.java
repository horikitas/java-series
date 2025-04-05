package org.horikita.java11features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

public class FileIOFeatures {

    public static void main(String[] args) throws IOException {
        java11FileIO();

        java8FileIO();
    }


    // Java 11 introduced new methods in the Files class to read and write strings to files.
    // This makes it easier to work with file I/O without needing to deal with byte streams or character encodings explicitly.
    // The Files.writeString() method allows you to write a string to a file, and the Files.readString() method allows you to read a string from a file.
    public static void java11FileIO() throws IOException {
        System.out.println("\n\nJava 11 File IO");
        Path path = Path.of("file.txt");
        Files.writeString(path, "Sasuke's Chidori Is The Best!\n" + Date.from(Instant.now()));


        String content = Files.readString(path);
        System.out.println(content);
    }

    public static void java8FileIO() throws IOException {
        System.out.println("\n\nJava 8 File IO");
        Path path = Paths.get("file1.txt");
        String content = "Shadow Hokage Mode"+ Date.from(Instant.now());

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(content);
        }

        String readContent;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            readContent = reader.lines().collect(Collectors.joining("\n"));
        }

        System.out.println(readContent);

    }
}
