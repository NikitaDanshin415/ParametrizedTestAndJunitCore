package homework.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DnsCatalog {
    private final SelenideElement brandBlock = $("[data-id='brand']");
    private final SelenideElement pageTitle = $("h1.title");

    public ElementsCollection getSelectedBrands() {
        return brandBlock
                .scrollTo()
                .$$(".ui-checkbox-group input[type='checkbox']:checked");
    }

    public DnsCatalog pageTitleIs(String text) {
        pageTitle
                .shouldHave(Condition.text(text));
        return this;
    }

}
