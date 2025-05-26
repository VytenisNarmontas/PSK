package com.example.psk.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * JSF backing bean for CDI-extensibility demos.
 */
@Named("testBean")
@RequestScoped
public class TestBean {

    @Inject
    private ExampleService exampleService;

    public String getGreeting() {
        return exampleService.greet();
    }
}
