package com.epam;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(getSupplier()).
                thenApplyAsync(String::toUpperCase)
                .thenAccept(System.out::println);

        List<String> input = Arrays.asList("data1", "data2");

        List<CompletableFuture<String>> collect = input.stream().map(d1 ->
                CompletableFuture.supplyAsync(d1::toUpperCase)).collect(Collectors.toList());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(collect.toArray(new CompletableFuture[0]));

        voidCompletableFuture.join();

        collect.forEach(stringCompletableFuture -> System.out.println(stringCompletableFuture.join()));
    }

    private static Supplier<String> getSupplier() {
        return () -> "Data";
    }
}
