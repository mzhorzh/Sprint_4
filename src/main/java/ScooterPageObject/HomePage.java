package ScooterPageObject;

import Core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class HomePage extends Driver {
    private final By orderHeaderButton = By.xpath(".//button[contains(@class,'Button_Button')]");
    private final By containerMiddleOrderButton = By.xpath(".//div[contains(@class,'Home_ThirdPart')]");
    private final By orderMiddleButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[text()='Заказать']");
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By accordionQuestion = By.xpath(".//div[@class='accordion__item']");

    //Метод нажатия на кнопку "Заказать" либо в хедере, либо в середине, в зависимости от параметра
    public void clickOrderButton(int indexOrderButton) {
        if (indexOrderButton == 1) {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(containerMiddleOrderButton));
            WebElement element = driver.findElement(containerMiddleOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderMiddleButton));
            driver.findElement(orderMiddleButton).click();
        }
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(orderHeaderButton));
        driver.findElement(orderHeaderButton).click();
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
