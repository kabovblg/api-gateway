//package com.blagovestkabov.apigateway.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//
//@Configuration
//@EnableWebFluxSecurity
//public class OktaOAuth2WebSecurity {
//
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        serverHttpSecurity
//                .authorizeExchange(authorizeExchangeSpec ->  authorizeExchangeSpec.anyExchange().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//                .oauth2Login(Customizer.withDefaults());
//        return serverHttpSecurity.build();
//    }
//}
