package com.example.sunmi_cloud_printer;

import com.sunmi.externalprinterlibrary.api.PrinterException;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProvider {

    public static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static <Res> Optional<Res> runFunctionWithException(Callable<Res> function) throws PrinterException {
        try {
            return Optional.ofNullable(CompletableFuture.supplyAsync(() -> {
                try {
                    return executor.submit(function).get();
                } catch (ExecutionException | InterruptedException ex) {
                    try {
                        throw Objects.requireNonNull(ex.getCause());
                    } catch (PrinterException printerException) {
                        throw new CompletionException(printerException);
                    } catch (Throwable ignored) {
                        ignored.printStackTrace();
                    }
                }
                return null;
            }).join());
        } catch (CompletionException ex) {
            try {
                throw Objects.requireNonNull(ex.getCause());
            } catch (PrinterException possible) {
                throw possible;
            } catch (Throwable ignored) {
            }
        }
        return Optional.empty();
    }

    public static void runFunctionWithException(ThrowingRunnable function) throws PrinterException {
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    return executor.submit(function).get();
                } catch (ExecutionException | InterruptedException ex) {
                    try {
                        throw Objects.requireNonNull(ex.getCause());
                    } catch (PrinterException printerException) {
                        throw new CompletionException(printerException);
                    } catch (Throwable ignored) {
                        ignored.printStackTrace();
                    }
                }
                return null;
            }).join();
        } catch (CompletionException ex) {
            try {
                throw Objects.requireNonNull(ex.getCause());
            } catch (PrinterException possible) {
                throw possible;
            } catch (Throwable ignored) {
            }
        }
    }


}
