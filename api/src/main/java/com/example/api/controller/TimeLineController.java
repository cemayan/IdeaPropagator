package com.example.api.controller;

import com.example.api.service.SharedItemService;
import com.example.api.model.SharedItem;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.reactivestreams.Publisher;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/timeline", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@org.springframework.context.annotation.Profile("classic")
public class TimeLineController {


    private final SharedItemService sharedItemService;
    private final ApplicationEventPublisher publisher;
    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;

    public TimeLineController(SharedItemService sharedItemService, ApplicationEventPublisher publisher) {
        this.sharedItemService = sharedItemService;
        this.publisher = publisher;
    }


    @GetMapping
    public Publisher<SharedItem> findAll() {
        return sharedItemService.all();
    }


    @PostMapping()
    public Publisher<ResponseEntity<SharedItem>> addNewItem(@RequestBody SharedItem sharedItem) {

        final String uri = "http://localhost:8081/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SharedItem> entity = new HttpEntity<SharedItem>(sharedItem, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return sharedItemService.create(sharedItem).map(p -> ResponseEntity.created(URI.create("/timeline/" + p.id))
                .contentType(mediaType)
                .build());
    }


    @DeleteMapping("/{id}")
    public Publisher<SharedItem> deleteItem(@PathVariable("id") String id) {
        return sharedItemService.delete(id);
    }


    @PutMapping("/{id}")
    public Publisher<SharedItem> updateItem(@PathVariable("id") String id, @Valid @RequestBody SharedItem sharedItem) {
        return sharedItemService.update(id, sharedItem);
    }

}
