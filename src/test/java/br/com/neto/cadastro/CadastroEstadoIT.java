package br.com.neto.cadastro;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroEstadoIT {
    
    @LocalServerPort
    private int port;
    
    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = this.port;
        RestAssured.basePath = "/v1/estados";
    }
    
    @Test
    public void deveRetornarStatus200_QuandoConsultarEstados() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter5Estados_QuandoConsultarEstados() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("_embedded.estados", hasSize(5));
    }

    @Test
    public void testRetornarStatus201_QuandoCadastrarEstado() {
        given()
            .body("{ \"nome\": \"Espírito Santo\", \"sigla\": \"Es\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

}
