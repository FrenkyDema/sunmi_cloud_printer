package com.example.lepsi_sunmi_cloud_printer;

import com.sunmi.externalprinterlibrary.api.PrinterException;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * The Task provider class.
 */
public class TaskProvider {

    /**
     * The constant executor.
     */
    public static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Run function with exception optional.
     *
     * @param <Res>    the type parameter
     * @param function the function
     * @return the optional
     * @throws PrinterException the printer exception
     */
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
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
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

    /**
     * Run function with exception.
     *
     * @param function the function
     * @throws PrinterException the printer exception
     */
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
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
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
