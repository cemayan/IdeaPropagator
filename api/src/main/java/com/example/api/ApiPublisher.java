package com.example.api;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
public class ApiPublisher implements ApplicationListener<ApiEvent>, Consumer<FluxSink<ApiEvent>> {

    private  final Executor executor;
    private  final BlockingQueue<ApiEvent> queue = new LinkedBlockingQueue<>();

    public ApiPublisher(Executor executor) {
        this.executor = executor;
    }


    @Override
    public void accept(FluxSink<ApiEvent> eventFluxSink) {
        this.executor.execute(() -> {

            while (true)
            {
                try {
                    ApiEvent apiEvent = queue.take();
                    eventFluxSink.next(apiEvent);

                } catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }


            }
        });
    }

    @Override
    public void onApplicationEvent(ApiEvent apiEvent) {
        this.queue.offer(apiEvent);
    }
}
