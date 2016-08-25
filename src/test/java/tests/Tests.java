package tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import misc.Ordered;
import misc.Ordered.Order;
import page.ListPage;
import page.LoginPage;
import page.NewShinseiPage;
import page.ShinseiPage;

@RunWith(Ordered.class)
public class Tests {

    @Test
    @Order(1)
    public void 初期データ() throws Exception {
        LoginPage loginPage = LoginPage.ログイン画面を開く();
        loginPage.ユーザーID.へ("backpaper0").をセットする();
        loginPage.パスワード.へ("backpaper0").をセットする();
        ListPage listPage = loginPage.ログインする();

        listPage.ユーザー名.は("うらがみ").である();

        listPage.明細の行数は(2).である();

        ListPage.Row row1 = listPage.明細の(1).行目();
        row1.概要は("「Javaエンジニア養成読本」購入申請").である();
        row1.ステータスは("承認").である();

        ListPage.Row row2 = listPage.明細の(2).行目();
        row2.概要は("「パーフェクトJava EE」購入申請").である();
        row2.ステータスは("申請中").である();

        listPage.ログアウトする();
    }

    @Test
    @Order(2)
    public void 新規登録() throws Exception {
        LoginPage loginPage = LoginPage.ログイン画面を開く();
        loginPage.ユーザーID.へ("backpaper0").をセットする();
        loginPage.パスワード.へ("backpaper0").をセットする();
        ListPage listPage = loginPage.ログインする();

        listPage.ユーザー名.は("うらがみ").である();

        NewShinseiPage newShinseiPage = listPage.新規申請を行う();

        newShinseiPage.概要.へ("ほにゃらー").をセットする();
        newShinseiPage.詳細.へ("ほげほげー").をセットする();
        ShinseiPage shinseiPage = newShinseiPage.登録する();

        shinseiPage.ログアウトする();
    }

    @Test
    @Order(3)
    public void 申請する() throws Exception {
        LoginPage loginPage = LoginPage.ログイン画面を開く();
        loginPage.ユーザーID.へ("backpaper0").をセットする();
        loginPage.パスワード.へ("backpaper0").をセットする();
        ListPage listPage = loginPage.ログインする();

        listPage.ユーザー名.は("うらがみ").である();

        listPage.明細の行数は(3).である();

        ListPage.Row row3 = listPage.明細の(3).行目();
        ShinseiPage shinseiPage = row3.詳細画面を開く();

        ShinseiPage.Yet yet = shinseiPage.ステータスは申請前();
        yet.概要は("ほにゃらー").である();
        yet.詳細は("ほげほげー").である();

        ShinseiPage.Applying applying = yet.申請する();
        applying.ステータスは申請中();

        applying.ログアウトする();
    }
}
