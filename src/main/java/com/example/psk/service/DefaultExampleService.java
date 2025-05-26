package com.example.psk.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultExampleService implements ExampleService {
    @Override
    public String greet() {
        return "Hello from DefaultExampleService";
    }
}
