package tests;

import config.BaseConfig;
import org.testng.annotations.Test;
import pages.HomePage;

public class Case2 extends BaseConfig {

    @Test
    public void testCase2() {
        // - добавить несколько товаров в корзину путем навигации по сайту
        HomePage homePage = new HomePage();
        homePage.selectRandomFromMainMenuCategories().
                selectRandomProduct().
                clickBuyBtn().
                clickContinueToBuyBtn();
        //homePage.selectRandomFromMainMenuCategories().selectRandomProduct().clickBuyBtn().clickContinueToBuyBtn().gotoHomePage();
        //homePage.selectRandomFromMainMenuCategories().selectRandomProduct().clickBuyBtn().clickContinueToBuyBtn();

        }

    /*Предусловия:


    Ожидаемые результаты:
            - выбраные товары присутсвуют в корзине
   - сумма в корзине соответсвует сумме товаров*/

}
