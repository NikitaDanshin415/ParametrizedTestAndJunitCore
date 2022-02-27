package homework;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import homework.pageobject.DnsBrandZone;
import homework.pageobject.DnsCatalog;
import homework.pageobject.DnsHome;
import homework.utils.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class ParametrizedMethodSourceTest extends BaseTest {
    static Stream<Arguments> MethodSourceTest() {
        return Stream.of(
                Arguments.of("Samsung", "Смартфоны"),
                Arguments.of("Apple", "Ноутбуки"),
                Arguments.of("HONOR", "Наушники и гарнитуры")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Ищем {1} от фирмы {0} на сайте DNS")
    void MethodSourceTest(String brand, String category) {
        open("");

        new DnsHome()
                .Search(brand);

        new DnsBrandZone()
                .metaExist(brand)
                .selectItem(category);

        new DnsCatalog()
                .pageTitleIs(category)
                .getSelectedBrands()
                .shouldHave(CollectionCondition.size(1)
                        .because("Должен быть выбран только один чек бокс " + brand))
                .get(0)
                .shouldHave(Condition.attribute("value", brand.toLowerCase(Locale.ROOT))
                        .because("Должен быть выбран только чек бокс " + brand));
    }
}
