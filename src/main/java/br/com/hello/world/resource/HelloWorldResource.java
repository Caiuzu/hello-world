package br.com.hello.world.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/v1/hello")
public class HelloWorldResource {

    private static final String PERSON_NAME_NULL = null;

    @GetMapping("/world")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/foo")
    public String helloFoo(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello, %s!", Objects.equals(name, PERSON_NAME_NULL) ? "World" : name);
    }
}
