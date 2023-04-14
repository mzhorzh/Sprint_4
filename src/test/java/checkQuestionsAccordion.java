import ScooterPageObject.HomePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class checkQuestionsAccordion {
    //Объявление переменных
    private final int index;
    private final String answerTextExpected;
    private WebDriver driver;

    //Консструктор параметров
    public checkQuestionsAccordion(int index, String answerTextExpected) {
        this.index = index;
        this.answerTextExpected = answerTextExpected;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    @Test
    public void checkAccordion() {
        //Устанавливаем драйвер для браузера Chrome
        WebDriver driver = new ChromeDriver();
        //Устанавливаем драйвер для браузера FireFox
        //WebDriver driver = new FirefoxDriver();
        //Загружаем главную страницу сайта
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Создаем объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //Закрываем окно Cookie
        objHomePage.clickCookieButton();
        //Скроллим до списка вопросов
        objHomePage.scrollToAccordion();
        //Ждем 4 секунды, чтобы страница доскроллилась
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        //Ожидаеем когда вопрос будет кликабелен
        objHomePage.waitQuestionToBeClickable();
        //Получаем и открываем номер вопроса
        objHomePage.clickAccordionQuestion(index);
        //Проверяем что отображается раскрытый ответ на вопрос
        objHomePage.checkAccordionQuestionPanelIsDisplayed(index);
        //Сравниваем текст ответа  сожидаемым текстом
        objHomePage.checkAccordionAnswerTextMatchesExpectedText(index, answerTextExpected);
        //Закрываем браузер
        driver.quit();
    }
}
