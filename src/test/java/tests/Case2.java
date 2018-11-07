package tests;

import config.BaseConfig;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MenuElements;

public class Case2 extends BaseConfig {

    @Test
    public void testCase2() {
        // - добавить несколько товаров в корзину путем навигации по сайту
        HomePage homePage = new HomePage();
        homePage.selectRandomMenuCategory();

        }

    /*Предусловия:


    Ожидаемые результаты:
            - выбраные товары присутсвуют в корзине
   - сумма в корзине соответсвует сумме товаров*/

}
