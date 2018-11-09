package tests;

import config.BaseConfig;
import org.testng.annotations.*;
import pages.*;

import java.util.List;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class Tests extends BaseConfig {

    @Test(priority = 0)
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
        homePage.gotoHomePage();
    }

    @Test(priority = 0)
    void cartsItemsTest() {
        int productsToTest = 2;
        float totalPriceForTest = 0f;
        TreeSet<String> productNamesForTest = new TreeSet<>();
        TreeSet<String> receivedProductNames;
        TreeSet<String> nameComparison = new TreeSet<>();

        HomePage homePage = new HomePage();
        ProductMenu productMenu = new ProductMenu();
        DialogCartMenu dialogCartMenu =new DialogCartMenu();

         // - добавить несколько товаров в корзину путем навигации по сайту
        for (int i = 0; i < productsToTest; i++) {
            totalPriceForTest += homePage.selectRandomMainMenuCategory().selectRandomProduct().getPrice();
            productNamesForTest.add(productMenu.getProductName());
            productMenu.clickBuyBtn().clickContinueToBuyBtn();
            homePage.gotoHomePage();
        }

        //- сумма в корзине соответсвует сумме товаров
        assertEquals(totalPriceForTest, homePage.clickCartBtn().getTotalPriceValue(), 0.0, "The total price in the basket is NOT corresponds to the sum of selected products.");
        dialogCartMenu.clickCloseCartBtn();
        homePage.gotoHomePage();

        //- выбраные товары присутсвуют в корзине
        receivedProductNames = homePage.clickCartBtn().getAllProductsNames();
        for (String productName : productNamesForTest){
            assertTrue(receivedProductNames.contains(productName), "Some products name are not equal.");
        }
        dialogCartMenu.deleteAllProducts();
        dialogCartMenu.clickCloseCartBtn();
        homePage.gotoHomePage();
    }

    @Test(priority = 0)
    void filtersCheck() {
        HomePage homePage = new HomePage();
        MenuElements menuElements = new MenuElements();
        String firstMenuStr = "SHA-256";
        String secondMenuStr = "В наличии";

        //- перейти на страницу Главная -> Асик майнеры (Asic) -> Асик майнеры (Asic)
        homePage.selectMainMenuCategory(0).clickOtherOptinsFilterElements(firstMenuStr); // 0 index for Asic category

        //- применить фильтр SHA-256 -> появляться Асик майнеры с текстом SHA-256 в ссылки на товар
        menuElements.getAllProductsNames();
        List<String> firstFilteredList = menuElements.getAllProductsNames();
        for (String productName:firstFilteredList
             ) {
            assertTrue(productName.contains(firstMenuStr), "Some product not contains checked string.");
        }

        //- отменить фильтр SHA-256 -> появляться Асик майнеры с текстом и без текста SHA-256 в ссылки на товар
        menuElements.clickOtherOptinsFilterElements(firstMenuStr);
        List<String> unfilteredFirstList = menuElements.getAllProductsNames();
        boolean containsNotOnlyFirstMenuStr = false;
             for (String productName:unfilteredFirstList
            ) {
                if (!productName.contains(firstMenuStr)) {
                    containsNotOnlyFirstMenuStr = true;
                    break;
                }
            }
            assertTrue(containsNotOnlyFirstMenuStr, "All products are contain checked string.");

        //- применить фильтр "В наличии" -> появляться Асик майнеры с текстом "В наличии" в ссылки на товар
        menuElements.getAllProductsNames();
        List<String> secondFilteredList = menuElements.getAllProductsNames();
        for (String productName:secondFilteredList
        ) {
            System.out.println(productName);
            assertTrue(productName.contains(secondMenuStr), "Some product not contains checked string.");
        }

        //- отменить фильтр "В наличии" -> появляться Асик майнеры с текстом и без текста "В наличии" в ссылки на товар
        menuElements.clickOtherOptinsFilterElements(secondMenuStr);
        List<String> secondUnfilteredList = menuElements.getAllProductsNames();
        boolean containsNotOnlySecondMenuStr = false;
        for (String productName:secondUnfilteredList
        ) {
            System.out.println(productName);
            if (!productName.contains(secondMenuStr)) {
                containsNotOnlySecondMenuStr = true;
                break;
            }
        }
        assertTrue(containsNotOnlySecondMenuStr, "All products are contain checked string.");
    }
}
