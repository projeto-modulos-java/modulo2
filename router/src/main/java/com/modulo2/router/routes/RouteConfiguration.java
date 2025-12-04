package com.modulo2.router.routes;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.*;
import static  org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.*;
import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.*;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouteConfiguration {

    @Value("${login-service.url}")
    private String loginUrl;

    @Bean
    public RouterFunction<ServerResponse> getRoute(){
        System.out.println(loginUrl);
        return route()
        .route(path("/auth/**") , http())
        .before(uri(loginUrl))
        .build();
    }
}
