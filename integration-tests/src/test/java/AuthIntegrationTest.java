import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class AuthIntegrationTest {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004";

    }

    @Test
    public void shouldReturnOKWithValidToken(){
        //1.arrange -->setUp data
        //2.act --> code triggers tetsing methods
        //3.assert -->assert repsone

        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;
        Response response = RestAssured.given().contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("jwtToken", IsNull.notNullValue())
                .extract().response();

        System.out.println("Generated token:"+response.jsonPath().getString("jwtToken"));

    }
    @Test
    public void shouldReturnUnauthorizedOnInvalidToken(){

        String payload  = """
                {
                "email":"wrong@email",
                "password":"wrong@password"
                }
                """;
        RestAssured.given().
                contentType("application/json")
                .body(payload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401)
                ;



    }
}
