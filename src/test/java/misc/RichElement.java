package misc;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.ElementsContainer;

public class RichElement extends ElementsContainer {

    public AssertionSuffix は(String text) {
        getSelf().should(text(text));
        return AssertionSuffix.INSTANCE;
    }

    public SetterSuffix へ(String text) {
        getSelf().val(text);
        return SetterSuffix.INSTANCE;
    }
}
