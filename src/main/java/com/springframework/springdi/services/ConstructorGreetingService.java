package com.springframework.springdi.services;

import org.springframework.stereotype.Service;


public class ConstructorGreetingService implements GreetingService{
    @Override
    public String sayGreeting() {
        return "Hello World – constructor";
    }
}
