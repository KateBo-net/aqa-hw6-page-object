package org.example.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement transferBtn = $("[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    public TransferPage() {
        amountField.shouldBe(visible);
    }

    public void topUpBalance(String amount, String cardNumberFrom) {
        clearField(amountField);
        clearField(cardNumberField);
        amountField.setValue(amount);
        cardNumberField.setValue(cardNumberFrom);
        transferBtn.click();
    }

    public DashboardPage doValidTransfer(String amount, String cardNumberFrom) {
        topUpBalance(amount, cardNumberFrom);
        return new DashboardPage();
    }

    public void clearField(SelenideElement field){
        field.sendKeys(Keys.CONTROL + "A");
        field.sendKeys(Keys.BACK_SPACE);
    }

    public void findErrorMsg(){
        errorNotification.shouldBe(visible, Duration.ofSeconds(5));
    }
}
