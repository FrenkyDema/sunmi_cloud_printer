package com.example.sunmi_cloud_printer;

import com.sunmi.externalprinterlibrary.api.PrinterException;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Utilities {

    public static <Res> Optional<Res> runFunctionWithException(Callable<Res> function) throws PrinterException {
        try {
            return Optional.ofNullable(CompletableFuture.supplyAsync(() -> {
                try {
                    return function.call();
                } catch (PrinterException ex) {
                    throw new CompletionException(ex);
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
                return null;
            }).join());
        } catch (CompletionException ex) {
            try {
                throw ex.getCause();
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
                    function.tryRun();
                } catch (PrinterException ex) {
                    throw new CompletionException(ex);
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
                return null;
            }).join();
        } catch (CompletionException ex) {
            try {
                throw ex.getCause();
            } catch (PrinterException possible) {
                throw possible;
            } catch (Throwable ignored) {
            }
        }
    }

}


