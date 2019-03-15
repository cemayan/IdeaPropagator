package com.example.api.config;

import com.example.api.handler.SharedItemHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SharedItemEndPointConf {

    @Bean
    RouterFunction<ServerResponse> routes(SharedItemHandler handler) { // <1>
        return route(i(GET("/timeline")), handler::all) // <2>
                .andRoute(i(GET("/timeline/{id}")), handler::getById)
                .andRoute(i(DELETE("/timeline/{id}")), handler::deleteById) // <3>
                .andRoute(i(POST("/timeline")), handler::create)
                .andRoute(i(PUT("/timeline/{id}")), handler::updateById);
    }


    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }

}
