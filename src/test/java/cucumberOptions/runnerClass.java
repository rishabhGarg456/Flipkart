package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(  
        plugin = { "pretty",
                "html:target/cucumber-reports/report.html", "json:target/cucumber-reports/report2.json"
        },
            features = {"src/test/resources/FeatureFiles"},
             glue = "stepDefinetion",
            dryRun = false,
            tags = "@tag2"
    )

public class runnerClass extends AbstractTestNGCucumberTests{

}
