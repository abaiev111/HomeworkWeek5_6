package com.gmail.aba.task1;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        JsonToXMLClass jsonToXMLClass = new JsonToXMLClass();

        ExecutorService executor = Executors.newFixedThreadPool(8);
        for(File file : jsonToXMLClass.getFiles()) {
            CompletableFuture.runAsync(() -> {
                jsonToXMLClass.run();
            }, executor);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {}

        jsonToXMLClass.writeToXML(jsonToXMLClass.sortedMap());

        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds \n");
    }
}

//1 thread: 2,66900 seconds
//2 thread: 2,60400 seconds
//4 thread: 3,21000 seconds
//8 thread: 3,85400 seconds