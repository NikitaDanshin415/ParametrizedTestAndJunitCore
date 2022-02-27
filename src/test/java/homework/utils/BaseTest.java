package homework.utils;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void precondition() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.dns-shop.ru";
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
