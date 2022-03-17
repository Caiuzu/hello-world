package br.com.hello.world.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberContextConfiguration()
@CucumberOptions(features = "src/test/resources/features", glue = "br.com.hello.world.cucumber.step")
public class CucumberTest {
}

