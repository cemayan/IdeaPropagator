package com.example.api;

import com.example.api.data.Data;
import com.example.api.model.Role;
import com.example.api.model.User;
import com.example.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories("com.example.api.repository")
public class ApiApplication implements CommandLineRunner   {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    UserService userService;


    @Override
    public void run(String... params) throws Exception {

        userService.deleteAll();

        User admin = new User();
        admin.username = "admin";
        admin.password = "admin";
        admin.email = "admin@email.com";
        admin.roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN));

        userService.signup(admin);

        User client = new User();
        client.username = "client";
        client.password = "client";
        client.email = "client@email.com";
        client.roles = new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT));

        userService.signup(client);
    }


}
