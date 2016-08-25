package page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import misc.AssertionSuffix;

public class ShinseiPage {

    @FindBy(css = "#logout")
    private SelenideElement logout;
    @FindBy(css = ".panel-heading")
    protected ElementsCollection panelHeadings;
    @FindBy(css = ".panel-body")
    protected ElementsCollection panelBodies;
    @FindBy(css = "#apply")
    protected SelenideElement apply;

    public LoginPage ログアウトする() {
        logout.click();
        switchTo().alert().accept();
        return page(LoginPage.class);
    }

    public ShinseiPage.Yet ステータスは申請前() {
        apply.should(exist);
        return page(ShinseiPage.Yet.class);
    }

    public ShinseiPage.Applying ステータスは申請中() {
        apply.should(hidden);
        panelHeadings.get(0).shouldBe(text("申請中"));
        return page(ShinseiPage.Applying.class);
    }

    public static class Yet extends ShinseiPage {

        public AssertionSuffix 概要は(String text) {
            panelBodies.get(0).shouldBe(text(text));
            return AssertionSuffix.INSTANCE;
        }

        public AssertionSuffix 詳細は(String text) {
            panelBodies.get(1).shouldBe(text(text));
            return AssertionSuffix.INSTANCE;
        }

        public Applying 申請する() {
            apply.click();
            switchTo().alert().accept();
            switchTo().alert().accept();
            return page(ShinseiPage.Applying.class);
        }
    }

    public static class Applying extends ShinseiPage {

    }
}
