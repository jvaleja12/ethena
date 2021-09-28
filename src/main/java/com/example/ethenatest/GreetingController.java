package com.example.ethenatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jyotivaleja
 * GreetingController: This class will have all the apis
 */
public class GreetingController {

    @Autowired
    AmaflixManager amaflixManager;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public void greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
       // amaflixManager.getRecommendations();
    }
}
