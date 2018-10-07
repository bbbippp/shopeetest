package TestRunner;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Ian Gumilang
 */

@RunWith(Cucumber.class)
@Cucumber.Options(features = "src/main/java/features", glue = {"StepDefinition"})
public class Runner
{

}