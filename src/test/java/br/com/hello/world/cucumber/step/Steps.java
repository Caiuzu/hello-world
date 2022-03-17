package br.com.hello.world.cucumber.step;


import br.com.hello.world.resource.HelloWorldResource;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Steps {

    private HelloWorldResource helloWorldResource;
    private String result;

    @Dado("que acessou a função de Hello World")
    public void thatAccessedHelloWorldFunction() {
        helloWorldResource = new HelloWorldResource();
        result = helloWorldResource.helloWorld();
    }

    @Então("deverá ser exibido a mensagem de Hello, World! com sucesso")
    public void helloWorldMessageShouldBeDisplayedWithSuccess() {
        assertNull(result);
    }

    @Dado("que acessou a função de Hello Foo")
    public void thatAccessedHelloFooFunction() {
        helloWorldResource = new HelloWorldResource();
    }

    @Quando("passado o parâmetro {string}")
    public void withParameter(final String parameter) {
        result = helloWorldResource.helloFoo(parameter);
    }

    @Quando("não passado parâmetro")
    public void withoutParameter() {
        result = helloWorldResource.helloFoo(null);
    }

    @Então("deverá ser exibido a mensagem de {string} com sucesso")
    public void messageShouldBeDisplayedWithSuccess(final String parameter) {
        assertEquals(result, parameter);
    }

}
