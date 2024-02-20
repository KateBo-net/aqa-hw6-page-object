package org.example.page;

import com.codeborne.selenide.SelenideElement;
import org.example.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement sendButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo info){
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        sendButton.click();
        return new VerificationPage();
    }

}
