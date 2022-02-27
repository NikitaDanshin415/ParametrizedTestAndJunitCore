package homework.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DnsHome {
    private final SelenideElement SearchInput = $("#header-search [name='q']");

    public DnsHome Search(String text){
        SearchInput
                .setValue(text)
                .pressEnter();

        return this;
    }
}
