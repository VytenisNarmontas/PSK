package com.example.psk.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("testBean")
@RequestScoped
public class TestBean {

    @Inject
    private ExampleService exampleService;

    public String getGreeting() {
        return exampleService.greet();
    }
}
