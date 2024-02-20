package org.example.page;

import com.codeborne.selenide.SelenideElement;
import org.example.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement transferBtn = $("[data-test-id=action-transfer]");
    public TransferPage() {
        amountField.shouldBe(visible);
    }

    public DashboardPage topUpBalance(int amount, DataHelper.CardInfo cardFrom){
        amountField.setValue(String.valueOf(amount));
        cardNumberField.setValue(cardFrom.getNumber());
        transferBtn.click();
        return new DashboardPage();
    }
}
