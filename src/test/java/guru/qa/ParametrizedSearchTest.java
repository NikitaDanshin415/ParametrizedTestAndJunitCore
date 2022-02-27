package guru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSearchTest {
    @BeforeEach
    void precondition() {
        open("http://yandex.ru");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "JUnit5"})
    @ParameterizedTest(name = "\"Проверка отображения поисковых результатов в яндексе для запрос {0}")
    void commonSearchTest(String text) {
        $("#text").setValue(text);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(text)).shouldBe(Condition.visible);
    }

    @CsvSource(value = {
            "Selenide| concise UI tests in Java",
            "JUnit5| IntelliJ IDea"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "\"Проверка отображения поисковых результатов в яндексе для запрос {0}, {1}")
    void complexSearchTest(String text, String expectedText) {
        $("#text").setValue(text);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(expectedText)).shouldBe(Condition.visible);
    }


    static Stream<Arguments> mixedSearchTest(){
        return Stream.of(
                Arguments.of("Selenide", List.of(1,2,3)),
                Arguments.of("JUnit5", List.of(1,2,3))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "\"Проверка отображения поисковых результатов в яндексе для запрос {0}, {1}")
    void mixedSearchTest(String firstArg, List<Integer> secondArg) {
        System.out.println("String: " + firstArg + ", list: " + secondArg.toString());
    }

}
