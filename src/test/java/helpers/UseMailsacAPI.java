package helpers;

import static helpers.TestData.MAIL_DOMAIN;
import static helpers.TestData.SUCCEED_REGISTRATION_MAIL_SUBJECT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UseMailsacAPI {

    private static final String URL = "https://mailsac.com/api";
    private static final String CREATE_BOX_ENDPOINT = "/addresses/";
    private static final String MESSAGES_ENDPOINT = "/messages";
    private static final String API_KEY = "rbvt40Sm1QgVoFyvsDp63uvAMXbN1ppu";
    private static final String HOST_HEADER = "mailsac.com";

    public static void getSpecificPrivateAddress(String uuid) {

        given().given().headers("Mailsac-Key", API_KEY, "Host", HOST_HEADER).
                and().get(URL + CREATE_BOX_ENDPOINT + uuid + MAIL_DOMAIN).
                then().
                assertThat().statusCode(200).
                assertThat().statusLine("HTTP/1.1 200 OK").
                assertThat().contentType("application/json").
                assertThat().body("_id", equalTo(uuid + MAIL_DOMAIN));
    }

    public static void getListInboxEmailMessagesAndCheckSubject(String emailUuid) {

        given().given().headers("Mailsac-Key", API_KEY, "Host", HOST_HEADER).
                and().get(URL + CREATE_BOX_ENDPOINT + emailUuid + MAIL_DOMAIN + MESSAGES_ENDPOINT).
                then().
                assertThat().statusCode(200).
                assertThat().statusLine("HTTP/1.1 200 OK").
                assertThat().contentType("application/json").
                assertThat().body("subject[0]", equalTo(SUCCEED_REGISTRATION_MAIL_SUBJECT));
    }
}
