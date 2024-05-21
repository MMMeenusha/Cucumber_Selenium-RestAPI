package Tests;

import Pages.POJO.Location;
import Pages.POJO.placeID;
import Utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.juneau.json.JsonSerializer;
import org.jdom2.JDOMException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIStepDefinition {

    WebDriver driver;
    public TestContext testcontext;

    public APIStepDefinition(TestContext testcontext) throws JDOMException, IOException
    {
        this.testcontext = testcontext;
    }

    @Given("Create Input body through POJO Class")
    public void create_input_body_through_POJO_Class() throws Exception {
        testcontext.api.createBodyforPlaceID();
        testcontext.api.printJSON();
    }

    @When("Requesting response from Place API")
    public void requesting_response_from_place_API() throws IOException {
        testcontext.api.placeIDResponse();
    }

    @Then("Display the Place ID from response through POJO Class")
    public void display_the_placeID_from_response_through_POJO_class() {
        testcontext.api.getPlaceID();
    }
}
