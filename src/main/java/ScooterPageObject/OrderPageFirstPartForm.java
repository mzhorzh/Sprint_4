package ScooterPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPageFirstPartForm {
    //Локаторы
    private WebDriver driver;
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroList = By.className("select-search__row");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPageFirstPartForm(WebDriver driver){
        this.driver = driver;
    }

    //Метод проверки доступности, очиищения и ввода значения в поле "Имя"
    public void setNameValue(String name) {
        driver.findElement(nameField).isEnabled();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    //Метод проверки доступности, очиищения и ввода значения в поле "Фамилия"
    public void setSurnameValue(String surname) {
        driver.findElement(surnameField).isEnabled();
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }

    //Метод проверки доступности, очиищения и ввода значения в поле "Адрес"
    public void setAddressValue(String address) {
        driver.findElement(addressField).isEnabled();
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    //Метод выбора странции метро
    public void setMetroValue(int indexMetroStation) {
        driver.findElement(metroField).click();
        WebElement element = driver.findElements(metroList).get(indexMetroStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //Метод проверки доступности, очиищения и ввода значения в поле "Номер телефоона"
    public void setPhoneValue(String phone) {
        driver.findElement(phoneField).isEnabled();
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }

    //Метод нажатия на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}