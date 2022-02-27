package homework;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import homework.pageobject.DnsBrandZone;
import homework.pageobject.DnsCatalog;
import homework.pageobject.DnsHome;
import homework.utils.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class ParametrizedValueSourceTest extends BaseTest {
    @ValueSource(strings = {"Samsung", "Apple", "HONOR"})
    @ParameterizedTest(name = "Поиск товаров бренда {0} на сайте ДНС")
    void ValueSourceTest(String brand) {
        open("");

        new DnsHome()
                .Search(brand);

        new DnsBrandZone()
                .metaExist(brand)
                .selectItem("Смартфоны");

        new DnsCatalog()
                .getSelectedBrands()
                .shouldHave(CollectionCondition.size(1)
                        .because("Должен быть выбран только один чек бокс " + brand))
                .get(0)
                .shouldHave(Condition.attribute("value", brand.toLowerCase(Locale.ROOT))
                        .because("Должен быть выбран только чек бокс " + brand));
    }
}
