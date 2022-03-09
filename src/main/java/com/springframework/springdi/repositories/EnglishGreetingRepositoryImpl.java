package com.springframework.springdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {
    @Override
    public String getGreeting() {
        return "Hello World from Repository";
    }
}
