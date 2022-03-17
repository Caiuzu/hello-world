package br.com.hello.world.resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldResourceTest {

    public static final String HELLO_WORLD = "Hello, World!";
    public static final String HELLO_FOO = "Hello, Robson!";
    private static final String FOO_NAME = "Robson";
    private static final String PERSON_NAME_NULL = null;

    final HelloWorldResource resource;

    HelloWorldResourceTest() {
        resource = new HelloWorldResource();
    }

    @Test
    void getHelloWorldWithSuccess() {
        final String response = resource.helloWorld();
        assertEquals(HELLO_WORLD, response);
    }

    @Test
    void getHelloFooWithSuccess() {
        final String response = resource.helloFoo(FOO_NAME);
        assertEquals(HELLO_FOO, response);
    }

    @Test
    void getHelloFooWithNullParameterWithSuccess() {
        final String response = resource.helloFoo(PERSON_NAME_NULL);
        assertEquals(HELLO_WORLD, response);
    }

}