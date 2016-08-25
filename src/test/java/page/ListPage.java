package page;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import misc.AssertionSuffix;
import misc.RichElement;

public class ListPage {

    @FindBy(css = "#logout")
    private SelenideElement logout;
    @FindBy(linkText = "新規申請")
    private SelenideElement newShinsei;
    @FindBy(css = ".user-information")
    public RichElement ユーザー名;
    @FindBy(css = ".table tr")
    private ElementsCollection rows;

    public LoginPage ログアウトする() {
        logout.click();
        switchTo().alert().accept();
        return page(LoginPage.class);
    }

    public NewShinseiPage 新規申請を行う() {
        newShinsei.click();
        return page(NewShinseiPage.class);
    }

    public AssertionSuffix 明細の行数は(int expectedSize) {
        rows.shouldBe(size(expectedSize + 1));
        return AssertionSuffix.INSTANCE;
    }

    public RowBuilder 明細の(int index) {
        return new RowBuilder(index);
    }

    public class RowBuilder {
        private final int index;

        public RowBuilder(int index) {
            this.index = index;
        }

        public Row 行目() {
            return new Row(index);
        }
    }

    public class Row {

        private int index;

        public Row(int index) {
            this.index = index;
        }

        public AssertionSuffix 概要は(String text) {
            SelenideElement tr = rows.get(index);
            SelenideElement td = tr.$$("td").get(0);
            td.shouldBe(text(text));
            return AssertionSuffix.INSTANCE;
        }

        public AssertionSuffix ステータスは(String text) {
            SelenideElement tr = rows.get(index);
            SelenideElement td = tr.$$("td").get(1);
            td.shouldBe(text(text));
            return AssertionSuffix.INSTANCE;
        }

        public ShinseiPage 詳細画面を開く() {
            SelenideElement tr = rows.get(index);
            SelenideElement td = tr.$$("td").get(0);
            SelenideElement a = td.$("a");
            a.click();
            return page(ShinseiPage.class);
        }
    }
}
