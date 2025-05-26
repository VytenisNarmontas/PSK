package com.example.psk.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class AlternativeExampleService implements ExampleService {
    @Override
    public String greet() {
        return "Hello from AlternativeExampleService";
    }
}
