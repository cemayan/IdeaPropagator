package com.example.api;


import com.example.api.model.SharedItem;
import org.springframework.context.ApplicationEvent;

public class ApiEvent extends ApplicationEvent {

    public ApiEvent(SharedItem sharedItem) {
        super(sharedItem);
    }
}
