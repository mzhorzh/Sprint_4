package ScooterPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class HomePage {
    private final WebDriver driver;
    private final By orderHeaderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    private final By orderMiddleButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By accordionQuestion = By.xpath(".//div[@class='accordion__item']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод нажатия на кнопку в заголовке
    public void clickOrderHeaderButton() {
        driver.findElement(orderHeaderButton).click();
    }

    //Метод скролла к кнопке в середине страницы
    public void scrollOrderMiddleButton() {
        WebElement element = driver.findElement(orderMiddleButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод клика на кнопку в середине страницы
    public void clickOrderMiddleButton() {
        driver.findElement(orderMiddleButton).click();
    }

    //Метод скролла до списка вопросов
    public void scrollToAccordion() {
        WebElement element = driver.findElement(accordionQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //
    public void waitQuestionToBeClickable() {
        WebElement element = new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(accordionQuestion));
    }

    //Метод получения порядкового номера вопроса и клика на него
    public void clickAccordionQuestion(int index) {
        driver.findElements(accordionQuestion).get(index).click();
    }

    //Метод проверки отображения раскрытого ответа
    public void checkAccordionQuestionPanelIsDisplayed(int index) {
        WebElement accordionQuestionPanel = driver.findElements(accordionQuestion).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']"));

        assertTrue("Панель должна отображаться", accordionQuestionPanel.isDisplayed());
    }

    //Метод получения текста из вопроса и сравнения текста с ожидаемым текстом
    public void checkAccordionAnswerTextMatchesExpectedText(int index, String answerTextExpected) {
        String accordionQuestionText = driver.findElements(accordionQuestion).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']/p"))
                .getText();

        assertTrue(accordionQuestionText.equals(answerTextExpected));
    }

    //Метод нажатия на копку закрытия куков
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }
}
