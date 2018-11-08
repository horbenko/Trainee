package tests;

import config.BaseConfig;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pages.HomePage;
import pages.AccountArea;
import pages.MenuElements;
import pages.ProductCart;

import java.util.List;

import static org.testng.Assert.assertTrue;

class Tests extends BaseConfig {


/*
    @Test
    void loginUserTest() {
        HomePage homePage = new HomePage();

        //-залогиниться под валидными данными
        assertTrue(homePage.clickSingInBtn().loginUser("my_test_mail@meta.ua", "mytestpassword"), "Login is failed.");

        //- ссылка "Войти" должна замениться на "Выйти"
        AccountArea accountArea = new AccountArea();
        assertTrue(accountArea.isExitTextPresent(), "The Войти link is NOT replaced with the Выйти.");

        //- ссылка "Контакты" должна замениться на "ФИО" пользователя
        assertTrue(accountArea.isCorrectAccountFullName(), "The Контакты link is NOT replaced with the user's full name.");

        // - пользователь перенаправлен на страницу личного кабинета
        assertTrue(accountArea.isAccountArea(), "User is NOT redirected to personal account page.");
    }
*/

    @Test
    void cartsItemsTest() {

        HomePage homePage = new HomePage();
        ProductCart productCart = new ProductCart();

        // - добавить несколько товаров в корзину путем навигации по сайту
        for (int i = 0; i < 2; i++) {
            homePage.selectRandomMainMenuCategory().
                    selectRandomProduct().
                    clickBuyBtn().
                    clickContinueToBuyBtn();
            homePage.gotoHomePage();
        }
    }

    /*Предусловия:
    Ожидаемые результаты:
   - выбраные товары присутсвуют в корзине
   - сумма в корзине соответсвует сумме товаров*/

}
