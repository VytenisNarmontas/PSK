package com.example.psk.service;

import javax.enterprise.context.ApplicationScoped;

/**
 * The default implementation of ExampleService.
 */
@ApplicationScoped
public class DefaultExampleService implements ExampleService {
    @Override
    public String greet() {
        return "Hello from DefaultExampleService";
    }
}
