package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {

    @Test
    public void shouldSendOrder() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").val("Тверь");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").val(planningDate);
        form.$("[data-test-id=name] input").val("Иван Петров");
        form.$("[data-test-id=phone] input").val("+79887554433");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        //$("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}


