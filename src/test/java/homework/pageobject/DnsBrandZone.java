package homework.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DnsBrandZone {

    private final ElementsCollection items = $$("span[data-role='title-text']");

    public DnsBrandZone metaExist(String text) {
        $("meta[content='Все товары, новинки и акции от " + text + " в интернет магазине DNS.']")
                .shouldBe(Condition.exist.because("Должны отображаться мета данные найденной страницы"));

        return this;
    }

    public DnsBrandZone selectItem(String text) {
        items
                .find(Condition.text(text))
                .click();

        return this;
    }
}
