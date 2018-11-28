package RESTAssuredDemo;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Laptop on 11/21/2018.
 */

public class Demo {

    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                    get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                    assertThat().
                    body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
                    System.out.println("Number of Circuits validated");
    }

    @Test
    public void test_ResponseHeaderData_ShouldBeCorrect() {

        given().
                when().
                    get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                    assertThat().
                    statusCode(200).
                and().
                    contentType(ContentType.JSON).
                and().
                    header("Content-Length",equalTo("4551"));
    }
    @Test
    public void test_Md5CheckSumForTest_ShouldBe098f6bcd4621d373cade4e832627b4f6() {

        String originalText = "test";
        String expectedMd5CheckSum = "098f6bcd4621d373cade4e832627b4f6";

        given().
                param("text",originalText).
                when().
                    get("http://md5.jsontest.com").
                then().
                    assertThat().
                    body("md5",equalTo(expectedMd5CheckSum));
    }

    @Test
    public void test_NumberOfCircuits_ShouldBe20_Parameterized() {

        String season = "2017";
        int numberOfRaces = 20;

        given().
                pathParam("raceSeason",season).
                when().
                    get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                    assertThat().
                    body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
    }

    @Test
    public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry_ShouldBeAustralia() {

        // First, retrieve the circuit ID for the first circuit of the 2017 season
        String circuitId = given().
                when().
                    get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                    extract().
                    path("MRData.CircuitTable.Circuits.circuitId[0]");
        System.out.println("circuitId: " + circuitId);

        // Then, retrieve the information known for that circuit and verify it is located in Australia
        given().
                pathParam("circuitId",circuitId).
                when().
                    get("http://ergast.com/api/f1/circuits/{circuitId}.json").

                then().
                    assertThat().
                    body("MRData.CircuitTable.Circuits.Location[0].country",equalTo("Australia"));
    }


}
