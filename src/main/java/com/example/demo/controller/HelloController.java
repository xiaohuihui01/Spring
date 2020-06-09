package com.example.demo.controller;
import com.example.demo.UserRegisterRequest;
import com.example.demo.bean.ResponseBean;
import com.example.demo.response.GreetingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello,world";
    }
    private final AtomicLong counter = new AtomicLong();
    @CrossOrigin(origins="*")
    @RequestMapping("/greeting")
    public GreetingResponse greeting(@RequestParam(value = "name", defaultValue =
            "World") String name) {
        return new GreetingResponse(counter.incrementAndGet(), "Hello, " + name +
                "!");
    }

}