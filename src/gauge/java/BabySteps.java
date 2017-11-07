package gauge.java;

import com.thoughtworks.gauge.Step;

public class BabySteps extends cases.PapaBless{

    @Step("Go to <Url>")
    public void goToUrl(String url) {
        System.out.println("Going to " + url);
        System.out.println("Well aint you a high falootin' dandy? \n");
    }

    @Step("Divide <dividend> by <divisor>")
    public void eyB0ssCanIHabeDibisionPrease(int top, int bottom) {
        System.out.println("You couldn't divide " + top + " by " + bottom + " and get " + top/bottom + " like a normal person? This is why you talk to yourself in your apartment at night.");
    }

//    @Step("imma firin mah lazer")
//    public void


}
