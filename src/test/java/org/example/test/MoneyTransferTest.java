package org.example.test;

import org.example.data.DataHelper;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    DataHelper.CardInfo firstCard;
    DataHelper.CardInfo secondCard;
    DashboardPage dashboardPage;

    @BeforeEach
    void setup() throws InterruptedException {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor(authInfo));
        firstCard = DataHelper.getCardInfo(0, dashboardPage.getCardBalance(0));
        secondCard = DataHelper.getCardInfo(1, dashboardPage.getCardBalance(1));
        dashboardPage.resetBalance(firstCard, secondCard);
    }

    @Test
    public void shouldTransferFromFirstCard(){
        var transferPage = dashboardPage.goToTransferPage(1);
        var amount = DataHelper.generateValidAmount(firstCard.getBalance());
        dashboardPage = transferPage.topUpBalance(amount, firstCard);

        int actualFrom = dashboardPage.getCardBalance(1);
        int expectedFrom = firstCard.getBalance() + amount;

        int actualTo = dashboardPage.getCardBalance(0);
        int expectedTo = secondCard.getBalance() - amount;

        Assertions.assertEquals(expectedFrom, actualFrom);
        Assertions.assertEquals(expectedTo, actualTo);
    }

    @Test
    public void shouldTransferFromSecondCard(){
        var transferPage = dashboardPage.goToTransferPage(0);
        var amount = DataHelper.generateValidAmount(secondCard.getBalance());
        dashboardPage = transferPage.topUpBalance(amount, secondCard);

        int actualTo = dashboardPage.getCardBalance(0);
        int expectedTo = firstCard.getBalance() + amount;

        int actualFrom = dashboardPage.getCardBalance(1);
        int expectedFrom = secondCard.getBalance() - amount;

        Assertions.assertEquals(expectedFrom, actualFrom);
        Assertions.assertEquals(expectedTo, actualTo);
    }

    @Test
    public void shouldNotTransferMoreThanBalance() throws InterruptedException {
        var transferPage = dashboardPage.goToTransferPage(0);
        var amount = secondCard.getBalance() + 1;
        dashboardPage = transferPage.topUpBalance(amount, secondCard);
        Thread.sleep(5000);

        int actualTo = dashboardPage.getCardBalance(0);
        int expectedTo = firstCard.getBalance();

        int actualFrom = dashboardPage.getCardBalance(1);
        int expectedFrom = secondCard.getBalance();

        Assertions.assertEquals(expectedFrom, actualFrom);
        Assertions.assertEquals(expectedTo, actualTo);
    }
}
