package ScooterPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class OrderPageSecondPartForm {
    //Локаторы
    private WebDriver driver;

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentTimeField = By.className("Dropdown-arrow");
    private final By rentTimeList = By.className("Dropdown-option");
    private final By colorCheckBoxField = By.xpath(".//label[@class='Checkbox_Label__3wxSf']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By modalYesButton = By.xpath(".//button[text()='Да']");
    private final By modalOrderNumberWindow = By.className("Order_Modal__YZ-d3");

    public OrderPageSecondPartForm(WebDriver driver) {
        this.driver = driver;
    }

    ////Метод проверки доступности, очиищения и ввода значения в поле "Дата"
    public void setDateValue(String date) {
        driver.findElement(dateField).isEnabled();
        driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(date);
    }

    //Метод выбора срока аренды
    public void setTimeValue(int indexRentTime) {
        driver.findElement(rentTimeField).click();
        WebElement element = driver.findElements(rentTimeList).get(indexRentTime);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //Метод выбора цвета самоката
    public void setColorCheckBox(int indexColour) {
        driver.findElements(colorCheckBoxField).get(indexColour).click();
    }

    //Метод проверки доступности, очиищения и ввода значения в поле "Комметарй"
    public void setCommentValue(String comment) {
        driver.findElement(commentField).isEnabled();
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }

    //Метод нажатия на кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Метод нажатия на кнопку "Да" в модальо окне
    public void clickModalYesButton() {
        driver.findElement(modalYesButton).click();
    }

    //Метод проверки что выводится модальное окно сс номером заказа
    public void checkOrderWindowIsDisplayed() {
        assertTrue("Должно быть окно с номером заказа", driver.findElement(modalOrderNumberWindow).isDisplayed());
    }
}
