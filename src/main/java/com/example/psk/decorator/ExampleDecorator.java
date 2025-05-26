package com.example.psk.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.example.psk.service.ExampleService;

@Decorator
public class ExampleDecorator implements ExampleService {

    @Inject
    @Delegate
    private ExampleService delegate;

    @Override
    public String greet() {
        // prefix the original greeting
        return "[Decorated] " + delegate.greet();
    }
}
