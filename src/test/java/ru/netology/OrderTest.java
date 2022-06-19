package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

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
//        LocalDate date = LocalDate.now();
//        LocalDate newDate = date.plusDays(4);
//        int year = newDate.getYear();
//        int month = newDate.getMonthValue();
//        int day = newDate.getDayOfMonth();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        String text = newDate.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").val("Тверь");
        //form.$("[data-test-id=date] input").clear();
        //form.$("[data-test-id=date] input").val(text);
        form.$("[data-test-id=name] input").val("Иван Петров");
        form.$("[data-test-id=phone] input").val("+79887554433");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }
}
