package com.example.stream.controller;

import com.example.stream.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin
public class SparkController {


    @Autowired
    StreamService streamService;

   @GetMapping()
    public  void get(){
        streamService.get();
   }

}
