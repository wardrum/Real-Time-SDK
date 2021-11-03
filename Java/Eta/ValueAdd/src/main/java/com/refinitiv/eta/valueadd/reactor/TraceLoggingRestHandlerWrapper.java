package com.refinitiv.eta.valueadd.reactor;

import java.util.function.BiConsumer;

import org.apache.http.concurrent.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to wrap a {@link FutureCallback} with to execute an action
 * before delegating to the wrapped callback
 */
public class TraceLoggingRestHandlerWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraceLoggingRestHandlerWrapper.class);

    private static class Wrapper<T> implements FutureCallback<T> {
        private final FutureCallback<T> delegate;
        private final BiConsumer<T, Exception> consumer;

        private Wrapper(FutureCallback<T> cb, BiConsumer<T, Exception> consumer) {
            this.delegate = cb;
            this.consumer = consumer;
        }

        @Override
        public void cancelled() {
            safeTraceLog(null, null);
            delegate.cancelled();
        }

        @Override
        public void completed(T result) {
            safeTraceLog(result, null);
            delegate.completed(result);
        }

        @Override
        public void failed(Exception ex) {
            safeTraceLog(null, ex);
            delegate.failed(ex);

        }

        @Override
        public String toString() {
            return delegate.toString();
        }

        private void safeTraceLog(T result, Exception ex) {
            try {
                consumer.accept(result, ex);
            } catch (Exception e) {
                LOGGER.warn("Trace logging failed: {}", e.getMessage(), e);
            }
        }
    }

    private TraceLoggingRestHandlerWrapper() {
    }

    /**
     * Wrap the {@link FutureCallback} <strong>cb</strong> together with a
     * <strong>consumer</strong> of response entity or {@link Exception}. Values
     * will be set as follows:
     * <table>
     * <thead>
     * <tr>
     * <th>Outcome</th>
     * <th>R</th>
     * <th>Exception</th>
     * </tr>
     * </thead> <tbody>
     * <tr>
     * <td>completed</td>
     * <td>yes</td>
     * <td>no</td>
     * </tr>
     * <tr>
     * <td>failed</td>
     * <td>no</td>
     * <td>yes</td>
     * </tr>
     * <tr>
     * <td>cancelled</td>
     * <td>no</td>
     * <td>no</td>
     * </tr>
     * 
     * </tbody>
     * </table>
     * 
     * @param <R>      Type of entity passed on on successful completion
     * @param cb       The actual {@link FutureCallback}
     * @param consumer {@link Consumer} to be called when the wrapped functions are
     *                 called
     * @return A {@link FutureCallback} wrapping the actual {@link FutureCallback}
     *         <strong>cb</strong>, never <code>null</code>
     */
    public static <R> FutureCallback<R> wrap(FutureCallback<R> cb, BiConsumer<R, Exception> consumer) {
        return new Wrapper<>(cb, consumer);
    }

}
