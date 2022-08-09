package com.example.sunmi_cloud_printer;

@FunctionalInterface
public interface ThrowingRunnable extends Runnable {
    private static <E extends Exception> void throwUnchecked(Throwable t) throws E {
        throw (E) t;
    }

    @Override
    default void run() {
        try {
            tryRun();
        } catch (final Throwable t) {
            throwUnchecked(t);
        }
    }

    void tryRun() throws Throwable;
}