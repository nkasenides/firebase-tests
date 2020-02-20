package util;

import com.google.api.core.ApiFuture;

import java.util.concurrent.ExecutionException;

public class RDBExecutor {

    private static <T> T waitForFuture(ApiFuture<T> future) {
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Error while waiting for future", e);
        }
    }

    public static <T> void execute(ApiFuture<T> future) {
        waitForFuture(future);
    }

}
