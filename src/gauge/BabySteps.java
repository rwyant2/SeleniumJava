package gauge;

import com.thoughtworks.gauge.Step;

public class BabySteps {

    @Step("Go to <Url>")
    public void goToUrl(String url) {
        System.out.println("Going to " + url);
    }

}
