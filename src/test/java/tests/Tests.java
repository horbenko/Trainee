package tests;

import config.BaseConfig;
import io.qameta.allure.Description;
import org.testng.annotations.*;
import pages.*;

import java.util.List;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class Tests extends BaseConfig {

    @Test(description = "Login test for the registered user.")
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
        homePage.clickHomePage();
    }

    @Test(description = "Test to compare added and present products in the cart.")
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
            homePage.clickHomePage();
        }

        //- сумма в корзине соответсвует сумме товаров
        assertEquals(totalPriceForTest, homePage.clickCartBtn().getTotalPriceValue(), 0.0, "The total price in the basket is NOT corresponds to the sum of selected products.");
        dialogCartMenu.clickCloseCartBtn();
        homePage.clickHomePage();

        //- выбраные товары присутсвуют в корзине
        receivedProductNames = homePage.clickCartBtn().getAllProductsNamesInList();
        for (String productName : productNamesForTest){
            assertTrue(receivedProductNames.contains(productName), "Some products name are not equal.");
        }
        dialogCartMenu.deleteAllProducts().clickCloseCartBtn();
        homePage.clickHomePage();
    }

    @Test(description = "Test to check filters.")
    void filtersCheck() {
        HomePage homePage = new HomePage();
        MenuElements menuElements = new MenuElements();
        String firstMenuStr = "SHA-256";
        String secondMenuStr = "В наличии";

        //- перейти на страницу Главная -> Асик майнеры (Asic) -> Асик майнеры (Asic)
        List<String> firstFilteredList = homePage.selectMainMenuCategory(0).
                                        //- применить фильтр SHA-256
                                        clickOtherOptionsFilterElements(firstMenuStr).
                                        getAllProductsNamesInList(); // 0 index for Asic category

        //-> появляться Асик майнеры с текстом SHA-256 в ссылки на товар
        for (String productName:firstFilteredList) {
            assertTrue(productName.contains(firstMenuStr), "Some product not contains checked string.");
        }

        //- отменить фильтр SHA-256 -> появляться Асик майнеры с текстом и без текста SHA-256 в ссылки на товар
        menuElements.clickOtherOptionsFilterElements(firstMenuStr);
        List<String> unfilteredFirstList = menuElements.getAllProductsNamesInList();
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
        menuElements.clickOtherOptionsFilterElements(secondMenuStr);
        List<String> secondFilteredList = menuElements.getAllProductsNamesInList();
        for (String productName:secondFilteredList) {
            assertTrue(productName.contains(secondMenuStr), "Some product not contains checked string.");
        }

        //- отменить фильтр "В наличии" -> появляться Асик майнеры с текстом и без текста "В наличии" в ссылки на товар
        menuElements.clickOtherOptionsFilterElements(secondMenuStr);
        List<String> secondUnfilteredList = menuElements.getAllProductsNamesInList();
        boolean containsNotOnlySecondMenuStr = false;
        for (String productName:secondUnfilteredList
        ) {
            if (!productName.contains(secondMenuStr)) {
                containsNotOnlySecondMenuStr = true;
                break;
            }
        }
        assertTrue(containsNotOnlySecondMenuStr, "All products are contain checked string.");
        homePage.clickHomePage();
    }

    //Проверка ссылок секции "Компания", "Покупателям" в футтере сайта
    // - последовательно перейти по каждой ссылке -> отображается страница корректная страница
    @Test(dataProvider = "getDataForFooterLinksCheck")
    @Description("Test to check footer links.")
    void footerLinkCheck(String linkText, String  title) {
        HomePage homePage = new HomePage();
        assertEquals(homePage.clickOnLinkByText(linkText).getPageTitle(), title);
        homePage.getHomePage();
    }

    @DataProvider
    public Object[][] getDataForFooterLinksCheck() {
        return new Object[][]{
                {"Статьи", "Список новостей Рубрика: Наши обзоры"},
                {"Новости", "Список новостей"},
                {"Контакты", "Связаться с нами"},
                {"О компании СМТ", "О Компании СМТ - Интернет магазин SMT.UA"},
                {"Миссия компании СМТ", "Миссия компании СМТ - Интернет магазин SMT.UA"},
                {"Сертификаты", "Сертификаты - Интернет магазин SMT.UA"},
                {"Вакансии", "Вакансии - Интернет магазин SMT.UA"},
                {"Карта сайта", "Карта сайта"},
                {"Собрать компьютер", "Собрать компьютер в Харькове, системный блок, сборка конфигурации системника онлайн в Украине, Киеве, Одессе, Донецке, Днепропетровске – Интернет магазин SMT.UA"},
                {"Оплата и доставка", "Оплата и доставка майнинг оборудования по Украине - Интернет магазин SMT."},
                {"Сервис и гарантия", "Сервисный центр - интернет-магазин smt.ua"},
                {"Сотрудничество", "Оптовый поставщик компьютеров, планшетов, ноутбуков, комплектующих, компьютерной техники, компьютеры оптом, опт компьютеры, ноутбуки оптом, опт ноутбуки. - Интернет магазин SMT.UA"},
                {"Каталог производителей", "Каталог производителей электроники, компьютерной техники и бытовой техники в Украине - интернет магазин SMT.UA" }
        };
    }
}
