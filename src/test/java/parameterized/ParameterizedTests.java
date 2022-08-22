package parameterized;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;

public class ParameterizedTests {

    @ValueSource(strings = {"Кошки","Собаки"})
    @ParameterizedTest(name = "Элементы меню {0} отображаются.")
    void checkMenuItemVisibleTest(String testData){
        open("https://4lapy.ru/");
        $(".b-header__menu").$(byText(testData)).shouldBe(Condition.visible);
    }

    @CsvSource(value = {
            "Кошки,  Товары для кошек",
            "Собаки,  Товары для собак",
    })
    @ParameterizedTest(name = "При открытии пункта меню {0} заголовок страницы соответствует {1}")
    void checkHeaderTextTest(String testData, String expectedResult) {
        open("https://4lapy.ru/");
        $(".b-header__menu").$(byText(testData)).click();
        $(".b-title").shouldHave(text(expectedResult));
    }

    static Stream<Arguments> dataProviderForZooMenuTest() {
        return Stream.of(
                Arguments.of("Кошки", List.of("Корм для кошек", "Защита от блох, клещей, гельминтов для кошек", "Когтеточки и игровые комплексы для кошек",
                        "Лежаки и домики для кошек","Одежда и аксессуары для кошек", "Лакомства для кошек", "Наполнители для кошачьего туалета для кошек",
                        "Лотки и туалеты для кошек", "Умные товары для кошек", "Сумки, переноски и путешествия для кошек",
                        "Игрушки для кошек", "Миски, кормушки, поилки для кошек", "Груминг для кошек","Средства гигиены и косметика для кошек",
                        "Коррекция поведения и средства от запаха для кошек","Амуниция для кошек",
                        "Клетки, вольеры, двери для кошек","Завели котенка")),
                Arguments.of("Собаки", List.of("Корм для собак", "Летний сезон для собак", "Средства от блох, клещей и гельминтов для собак",
                        "Лакомства для собак", "Лежаки и домики для собак", "Амуниция и аксессуары для собак",
                        "Миски, кормушки, поилки для собак", "Сумки, переноски и путешествия для собак","Одежда, обувь и аксессуары для собак",
                        "Игрушки для собак","Умные товары для собак","Пеленки, подгузники, штанишки для собак","Туалеты и лотки для собак",
                        "Средства гигиены и косметика для собак","Инструменты груминга  для собак","Коррекция поведения и средства от запаха для собак",
                        "Клетки, вольеры, двери для собак","Завели щенка"))
        );
    }
    @MethodSource("dataProviderForZooMenuTest")
    @ParameterizedTest(name = "ри открытии пункта меню {0} отображаются ункты меню {1}")
    void checkMenuTest(String testData, List<String> expectedMenuItems) {
        open("https://4lapy.ru/");
        $(".b-header__menu").$(byText(testData)).click();
        $$(".b-accordion a").filter(visible).shouldHave(CollectionCondition.texts(expectedMenuItems));
    }
}





