package com.example.psk.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class SpecializedExampleService extends DefaultExampleService {
    @Override
    public String greet() {
        return "Hello from SpecializedExampleService";
    }
}
