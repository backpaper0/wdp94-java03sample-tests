package page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;

import misc.RichElement;

public class NewShinseiPage {

    @FindBy(css = "#description")
    public RichElement 概要;
    @FindBy(css = "#detail")
    public RichElement 詳細;
    @FindBy(css = "#register")
    private SelenideElement register;

    public ShinseiPage 登録する() {
        register.click();
        switchTo().alert().accept();
        switchTo().alert().accept();
        return page(ShinseiPage.class);
    }
}
