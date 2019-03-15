package com.example.api.service;

import com.example.api.ApiEvent;
import com.example.api.model.SharedItem;
import com.example.api.repository.SharedItemRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SharedItemService {

    private final ApplicationEventPublisher publisher; // <1>
    private final SharedItemRepository sharedItemRepository; // <2>

    SharedItemService(ApplicationEventPublisher publisher, SharedItemRepository sharedItemRepository) {
        this.publisher = publisher;
        this.sharedItemRepository = sharedItemRepository;
    }

    public Flux<SharedItem> all() { // <3>
        return this.sharedItemRepository.findAll();
    }

    public Mono<SharedItem> get(String id) { // <4>
        return this.sharedItemRepository.findById(id);
    }


    public Mono<SharedItem> update(String id, SharedItem sharedItem) { // <5>
        return this.sharedItemRepository
                .findById(id)
                .flatMap(existingItem -> {
                    existingItem.title = sharedItem.title;
                    existingItem.content = sharedItem.content;
                    return this.sharedItemRepository.save(existingItem);
                });
    }

    public Mono<SharedItem> delete(String id) { // <6>
        return this.sharedItemRepository
                .findById(id)
                .flatMap(p -> this.sharedItemRepository.deleteById(p.id).thenReturn(p));
    }

    public Mono<SharedItem> create(SharedItem sharedItem) { // <7>
        return this.sharedItemRepository
                .save(sharedItem)
                .doOnSuccess(profile -> this.publisher.publishEvent(new ApiEvent(profile)));
    }
}
