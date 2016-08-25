package page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import misc.RichElement;

public class LoginPage {

    @FindBy(css = "#userId")
    public RichElement ユーザーID;
    @FindBy(css = "#password")
    public RichElement パスワード;
    @FindBy(css = "#login")
    private SelenideElement login;

    public ListPage ログインする() {
        login.click();
        return page(ListPage.class);
    }

    public static LoginPage ログイン画面を開く() {
        return Selenide.open("/sample/app/shinsei", LoginPage.class);
    }
}
