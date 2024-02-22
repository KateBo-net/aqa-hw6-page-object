package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private ElementsCollection goToTransferBtn = $$(".list__item button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int index) {
        var text = cards.get(index).text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        var splitString = text.split(":")[1].split("р.");
        var result = splitString[0].trim();
        return Integer.parseInt(result);
    }

    public TransferPage goToTransferPage(int index) {
        goToTransferBtn.get(index).click();
        return new TransferPage();
    }

}
