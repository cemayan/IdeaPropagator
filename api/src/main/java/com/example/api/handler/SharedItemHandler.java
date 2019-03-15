package com.example.api.handler;

import com.example.api.model.SharedItem;
import com.example.api.service.SharedItemService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SharedItemHandler {

    private final SharedItemService sharedItemService;

    SharedItemHandler(SharedItemService sharedItemService) {
        this.sharedItemService = sharedItemService;
    }

    public Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.sharedItemService.get(id(r)));
    }

    public Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.sharedItemService.all());
    }

    public Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.sharedItemService.delete(id(r)));
    }

    public Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<SharedItem> id = r.bodyToFlux(SharedItem.class)
                .flatMap(p -> this.sharedItemService.update(id(r), p));
        return defaultReadResponse(id);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Flux<SharedItem> flux = request
                .bodyToFlux(SharedItem.class)
                .flatMap(toWrite -> this.sharedItemService.create(toWrite));
        return defaultWriteResponse(flux);
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<SharedItem> sharedItems) {
        return Mono
                .from(sharedItems)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/timeline/" + p.id))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .build()
                );
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<SharedItem> sharedItems) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(sharedItems, SharedItem.class);
    }

    private static String id(ServerRequest r) {
        return r.pathVariable("id");
    }
}
