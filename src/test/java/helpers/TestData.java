package helpers;

import java.util.UUID;

public class TestData {

    private static class RandomStringGenerator {
        static String generateString() {
            return UUID.randomUUID().toString();
        }
    }

    public static final String RANDOM_UUID = RandomStringGenerator.generateString();
    public static final String MAIL_DOMAIN = "@mailsac.com";
    public static final String MAIN_APPLICATION_PAGE = "https://prom.ua/";
    public static final String JOIN_NOW_URL = "https://prom.ua/join-now";
    public static final String LOGIN = "testexecutionuser+"+RANDOM_UUID+"@gmail.com";
    public static final String PASSWORD = "Qwerty123";
    public static final String SUCCEED_REGISTRATION_MAIL_SUBJECT = "Подтвердите email для дальнейшей работы с Prom.ua";
    public static final String SEARCH_URL = "https://prom.ua/search";
    public static final int OBJECT_COUNT = 45;
    public static final String CABINET_URL = "https://my.prom.ua/cabinet/edit-checklist";
    public static final String CABINET_TITLE = "Кабинет компании";

}
