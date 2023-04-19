import Core.Annotations;
import ScooterPageObject.HomePage;
import ScooterPageObject.OrderPageFirstPartForm;
import ScooterPageObject.OrderPageSecondPartForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class checkOrderPage extends Annotations {
    //Объявление переменных
    private final int indexOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final int indexMetroStation;
    private final String phone;
    private final String date;
    private final int indexRentTime;
    private final int indexColour;
    private final String comment;

    //Конструктор параметров
    public checkOrderPage(int indexOrderButton, String name, String surname, String address, int indexMetroStation, String phone, String date, int indexRentTime, int indexColour, String
                          comment) {
        this.indexOrderButton = indexOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.indexMetroStation = indexMetroStation;
        this.phone = phone;
        this.date = date;
        this.indexRentTime = indexRentTime;
        this.indexColour = indexColour;
        this.comment = comment;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][] {
                {1, "Тест", "Тестович", "Тверская 3", 54, "+79030001122", "25.06.2023", 2, 0, "Тестовый комментарий"},
                {2, "Ашот", "Петрович", "Новый Арбат 25", 18, "+79161541325", "17.05.2023", 1, 1, "Не звонить"},
        };
    }
    @Test
    public void checkOrder() {
        //Создаем объект класса главной страницы
        HomePage objHomePage = new HomePage();
        //Нажимаем на кнопку "Заказать"
        objHomePage.clickOrderButton(indexOrderButton);
        //Создаем объект класса первой части формы заказа
        OrderPageFirstPartForm objOrderPage = new OrderPageFirstPartForm();
        //Вводим значеие в поле "Имя"
        objOrderPage.setNameValue(name);
        //Вводим значеие в поле "Фамилия"
        objOrderPage.setSurnameValue(surname);
        //Вводим значеие в поле "Адрес"
        objOrderPage.setAddressValue(address);
        //Выбираем станцию метро
        objOrderPage.setMetroValue(indexMetroStation);
        //Вводим значеие в поле "Телефон"
        objOrderPage.setPhoneValue(phone);
        //Нажимаем на кнопку "Далее"
        objOrderPage.clickNextButton();
        //Создаем объект класса второй части формы заказа
        OrderPageSecondPartForm objOrderPageSecondPartForm = new OrderPageSecondPartForm();
        //Вводим дату
        objOrderPageSecondPartForm.setDateValue(date);
        //Выбираем время аренды
        objOrderPageSecondPartForm.setTimeValue(indexRentTime);
        //Выбираем цвет самоката
        objOrderPageSecondPartForm.setColorCheckBox(indexColour);
        //Вводим комментарий для курьера
        objOrderPageSecondPartForm.setCommentValue(comment);
        //Нажимаем на кнопку "Заказать"
        objOrderPageSecondPartForm.clickOrderButton();
        //В модальном окне нажимаем "Да"
        objOrderPageSecondPartForm.clickModalYesButton();
        //Проверяем есть ли модальное окно с заказом
        objOrderPageSecondPartForm.checkOrderWindowIsDisplayed();
    }
}
