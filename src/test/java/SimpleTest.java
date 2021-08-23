import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SimpleTest extends TestBase {


    @Test
    void positiveFillTest() {
        step("Open students registration form", () -> {
            open("/automation-practice-form");
        });
        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                $("#firstName").setValue("Ivan");
                $("#lastName").setValue("Petrov");
                $("#userEmail").setValue("Ivan@Petrov.com");
                $(byText("Male")).click();
                $("#userNumber").setValue("8922222222");
            });
            step("Set date", () -> {
                $("#dateOfBirthInput").clear();
                $(".react-datepicker__month-select").selectOption("May");
                $(".react-datepicker__year-select").selectOption("1988");
                $(".react-datepicker__day--0" + "10").click();
            });
            step("Set subjects", () -> {
                $("#subjectsInput").setValue("Art").pressEnter();
            });
            step("Set hobbies", () -> {
                $(byText("Sports")).click();
            });
            step("Upload image", () -> {
                $("#uploadPicture").uploadFromClasspath("1.jpg");
            });
            step("Set address", () -> {
                $("#currentAddress").setValue("Moscow, Lenina 1");
            });
            step("Set state", () -> {
                $("#react-select-3-input").setValue("NCR").pressEnter();
                $("#react-select-4-input").setValue("Delhi").pressEnter();
            });
            step("Submit form", () -> {
                $(byText("Submit")).scrollIntoView(true).click();
            });
        });


        step("Verify successful form submit", () -> {
            $(".modal-body").shouldHave(Condition.text("Ivan Petrov"));
            $(".modal-body").shouldHave(Condition.text("Ivan@Petrov.com"));
            $(".modal-body").shouldHave(Condition.text("Male"));
            $(".modal-body").shouldHave(Condition.text("8922222222"));
            $(".modal-body").shouldHave(Condition.text("10 May,1988"));
            $(".modal-body").shouldHave(Condition.text("Arts"));
            $(".modal-body").shouldHave(Condition.text("Sports"));
            $(".modal-body").shouldHave(Condition.text("1.jpg"));
            $(".modal-body").shouldHave(Condition.text("Moscow, Lenina 1"));
            $(".modal-body").shouldHave(Condition.text("NCR Delhi"));
        });
    }

}
