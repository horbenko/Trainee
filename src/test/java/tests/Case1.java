package tests;

import config.BaseConfig;
import org.testng.annotations.*;
import pages.HomePage;
import pages.AccountArea;

import static org.testng.Assert.assertTrue;

public class Case1 extends BaseConfig {

    @Test
    public void testCase1() {
        //-залогиниться под валидными данными
        HomePage homePage = new HomePage();
        assertTrue(homePage.clickSingInBtn().loginUser("my_test_mail@meta.ua", "mytestpassword"), "Login is failed.");

        //- ссылка "Войти" должна замениться на "Выйти"
        AccountArea accountArea = new AccountArea();
        assertTrue(accountArea.isExitTextVisible(), "The Войти link is NOT replaced with the Выйти.");

        //- ссылка "Контакты" должна замениться на "ФИО" пользователя
        assertTrue(accountArea.isCorrectAccountFullName(), "The Контакты link is NOT replaced with the user's full name.");

        // - пользователь перенаправлен на страницу личного кабинета
        assertTrue(accountArea.isAccountArea(), "User is NOT redirected to personal account page.");
    }

}
