package tests;

import config.BaseConfig;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginForm;
import pages.AccountArea;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Case1 extends BaseConfig {

    @Test
    public void testCase1() {
        final String checkStr1 = "Выйти";

        //-залогиниться под валидными данными
        HomePage homePage = new HomePage();
        homePage.clickSingIn();
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername();
        loginForm.setPassword();
        loginForm.clickLoginBtn();

        //- ссылка "Войти" должна замениться на "Выйти"
        AccountArea accountArea = new AccountArea();
        System.out.println(accountArea.getIconLockText());
        assertEquals(accountArea.getIconLockText(), checkStr1, "Войти link is NOT replaced with Выйти."); //TODO Незрозуміло чому отримуємо Войти. Потрібно чекати перед тим як поле оновиться?

        //- ссылка "Контакты" должна замениться на "ФИО" пользователя
        assertTrue(accountArea.isCorrectAccountFullName(), "The Контакты link is NOT replaced with the user's full name.");

        // - пользователь перенаправлен на страницу личного кабинета
        assertTrue(accountArea.isAccountArea(), "User is NOT redirected to personal account page.");
    }

}
