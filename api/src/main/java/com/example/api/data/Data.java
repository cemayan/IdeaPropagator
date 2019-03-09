package com.example.api.data;

import com.example.api.model.SharedItem;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class Data {


    public static void initializeAllData(final ReactiveMongoTemplate mongoTemplate) {


        final Mono<Void> initializeCollections =
               mongoTemplate
                .dropCollection(SharedItem.class)
                .then(mongoTemplate.createCollection(SharedItem.class, CollectionOptions.empty().size(104857600).capped())).then(); // max: 100MBytes


        SharedItem model = new SharedItem();
        model.content = "1221";
        model.id = "111";
        model.title = "first record";

        final Mono<Void> initializeData =
                mongoTemplate
                        .insert(model)
                        .then();

        initializeCollections.then(initializeData).block();

    }

    private Data() {
        super();
    }

}
