package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

/**
 * Created by Laptop on 11/22/2018.
 */

public class GetPostSteps {

    @Given("^I perform a GET operation for \"([^\"]*)\"$")
    public void i_perform_a_GET_operation_for(String url) throws Throwable {
        given().contentType(ContentType.JSON);
    }

    @When("^I perform a GET on the \"([^\"]*)\" post resource$")
    public void i_perform_a_GET_on_the_post_resource(String postsNumber) throws Throwable {
        when().get(String.format("http://localhost:3000/posts/%s", postsNumber)).
                then().body("author", is("Karthik KK"));

    }

    @Then("^I should see the Author name as \"([^\"]*)\"$")
    public void i_should_see_the_Author_name_as(String arg1) throws Throwable {
        System.out.println("-- The END --");

    }





}
