package com.example.api;

import com.example.api.model.SharedItem;
import com.example.api.repository.SharedItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;


@Log4j2
@Component
@org.springframework.context.annotation.Profile("demo")
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final SharedItemRepository repository; // <3>

    public DataInitializer(SharedItemRepository repository) {
            this.repository = repository;
        }

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {


        SharedItem model = new SharedItem();
        model.title = "131";
        model.content = "sda";
        model.id = "64652";

            repository
                    .deleteAll() // <4>
                    .thenMany(
                            Flux
                                    .just("A", "B", "C", "D")//<5>
                                    .map(name -> model) // <6>
                                    .flatMap(repository::save) // <7>
                    )
                    .thenMany(repository.findAll()) // <8>
                    .subscribe(); // <9>
        }
}
