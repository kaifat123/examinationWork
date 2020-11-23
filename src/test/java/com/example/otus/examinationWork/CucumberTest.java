package com.example.otus.examinationWork;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"summary",
                "junit:target/cucumber/result.xml",
                "json:target/cucumber/pagefactory.json"
                , "pretty"
        },
        glue = {"dev.rusatom.qa.steps", "dev.rusatom.qa.helper"},
        features = {"src/test/resources/features/"},
        tags = "@test",
        strict = true,
        monochrome = true

)
public class CucumberTest {
}