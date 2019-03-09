package com.example.api.controller;


import com.example.api.model.SharedItem;
import com.example.api.repository.SharedItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {


    @Autowired
    private SharedItemRepository sharedItemRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SharedItem> findAll() {
        return sharedItemRepository.findWithTailableCursorBy();
    }

    @PostMapping(value = "/add")
    public Mono<SharedItem> addNewSubject(@RequestBody SharedItem sharedItem) {

        final String uri = "http://localhost:8081/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SharedItem> entity = new HttpEntity<SharedItem>(sharedItem, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return sharedItemRepository.save(sharedItem);
    }
}
