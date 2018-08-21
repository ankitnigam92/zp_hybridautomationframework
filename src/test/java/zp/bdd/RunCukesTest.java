package zp.bdd;

/**
 * Created by AnkitNigam.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/zp/bdd/"},
        //tags = {"@Multifilter"},
        plugin = { "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json"
        })

public class RunCukesTest {

}
