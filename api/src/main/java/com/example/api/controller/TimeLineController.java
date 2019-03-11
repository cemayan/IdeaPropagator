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

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/timeline")
@CrossOrigin
public class TimeLineController {


    @Autowired
    private SharedItemRepository sharedItemRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<SharedItem> findAll() {
        return sharedItemRepository.findAll();
    }


    @PostMapping()
    public Mono<SharedItem> addNewItem(@RequestBody SharedItem sharedItem) {

        final String uri = "http://localhost:8081/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SharedItem> entity = new HttpEntity<SharedItem>(sharedItem, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return sharedItemRepository.save(sharedItem);
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteItem(@PathVariable("id") String id) {
        return sharedItemRepository.findById(id)
                .flatMap(p -> this.sharedItemRepository.deleteById(p.id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> updateItem(@PathVariable("id") String id, @Valid @RequestBody SharedItem sharedItem) {
        return sharedItemRepository.findById(id)
                .flatMap(existingItem -> {
                    existingItem.title = sharedItem.title;
                    existingItem.content = sharedItem.content;
                    return this.sharedItemRepository.save(existingItem);
                })
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
