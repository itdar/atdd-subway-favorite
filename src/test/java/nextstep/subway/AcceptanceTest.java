package nextstep.subway;

import io.restassured.RestAssured;
import nextstep.subway.auth.dto.TokenResponse;
import nextstep.subway.utils.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static nextstep.subway.member.MemberSteps.로그인_되어_있음;
import static nextstep.subway.member.MemberSteps.회원_생성_요청;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    public static final String EMAIL = "login@email.com";
    public static final String PASSWORD = "password";
    public static final String OTHER_EMAIL = "OTHER_login@email.com";
    public static final String OTHER_PASSWORD = "OTHER_password";
    private static final Integer AGE = 30;

    @LocalServerPort
    int port;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    protected TokenResponse 로그인_사용자;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();

        회원_생성_요청(EMAIL, PASSWORD, AGE);
        로그인_사용자 = 로그인_되어_있음(EMAIL, PASSWORD);
    }
}