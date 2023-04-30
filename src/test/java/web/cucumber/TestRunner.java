
package web.cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/features"},


        plugin = {"pretty", "html:target/testReport.html", "json:target/jsonReport.json", "rerun:target/rerun.txt"},
        //dryRun = false,
        monochrome = true,

        //tags = "@jr or @sanity or @sb or @kh or @va or @di or @webNav or @writereview or @rl "
       tags = "@jr or @sanity or @sb or @kh or @va or @di or @webNav or @writereview or @rl or @am"




)
public class TestRunner {
}