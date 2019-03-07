package com.example.api.controller;


import com.example.api.model.SharedItem;
import com.example.api.repository.SharedItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {


    @Autowired
    private SharedItemRepository sharedItemRepository;

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SharedItem> findAll() {
        return sharedItemRepository.findAll();
    }

}
