package com.example.api.controller;

import com.example.api.ApiEvent;
import com.example.api.ApiPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
public class ServerSentEventController {

    private  final Flux<ApiEvent> events;
    private  final ObjectMapper objectMapper;


    public ServerSentEventController(ApiPublisher eventApiPublisher, ObjectMapper objectMapper) {
        this.events = Flux.create(eventApiPublisher).share();
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/sse/timeline", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Flux<String> sharedItems() {
        return  this.events.map(pce -> {
            try {
                return objectMapper.writeValueAsString(pce);

            }catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
