package com.example.api.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllSharedContent(){
        return "cem";
    }
}
