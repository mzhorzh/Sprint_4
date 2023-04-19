package Core;

import ScooterPageObject.HomePage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public abstract class Annotations extends Driver {
    @Before
    public void setUp() {
        //Устанавливаем драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        //Устанавливаем драйвер для браузера FireFox
        //FirefoxOptions options = new FirefoxOptions();
        //options.addPreference("browser.startup.page", 3);
        //driver = new FirefoxDriver(options);
        //Загружаем главную страницу сайта
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Ждем 5 секунд, чтобы страница доскроллилась
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        //Создаем объект класса основной страницы
        HomePage homePage = new HomePage();
        //Закрываем плашку с куками
        homePage.clickCookieButton();
    }

    @After
    public void exit() {
        driver.quit();
    }
}
