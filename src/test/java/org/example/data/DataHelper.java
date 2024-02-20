package org.example.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    static String[] cardNumbers = {"5559 0000 0000 0001", "5559 0000 0000 0002"};

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String number;
        private int balance;
    }

    public static CardInfo getCardInfo(int index, int balance) {
        return new CardInfo(cardNumbers[index], balance);
    }



    public static int generateValidAmount(int balance) {
        return (new Random().nextInt(balance)+1);
    }
}
