package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * com.example.boot
 *      ApplicationController
 *
 * Spring Boot Execute Controller
 *
 * </pre>
 *
 *
 *
 *
 * @author junypooh
 * @see
 * @since 2016-11-29 오후 4:42
 */
@SpringBootApplication
@RestController
public class ApplicationController {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationController.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello Spring Boot Application!";
    }
}
