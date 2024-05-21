package Pages;

import Objects.ProductCheckoutObject;
import Pages.POJO.Location;
import Pages.POJO.placeID;
import Utils.TestData;
import Utils.XMLReader;
import Utils.sql;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.juneau.json.JsonSerializer;
import org.jdom2.JDOMException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIPage {

    public WebDriver driver;

    public TestData td;
    XMLReader xml;
    ProductCheckoutObject po;
    Utils.sql sql;

    public ExtentTest test;

    public placeID json;
    public Response resp;

    public APIPage(WebDriver driver) throws IOException, JDOMException {
        this.driver = driver;
        this.td = new TestData();
        this.xml = new XMLReader();
        this.sql = new sql();
        //this.test = test;
        this.po = PageFactory.initElements(driver, ProductCheckoutObject.class);
    }

    public void createBodyforPlaceID() throws Exception {
        test.pass("API test started ...");
        xml.loadXML("practise", "APIToGetPlaceID");

        json.setAccuracy(Integer.valueOf(xml.getText("setAccuracy")));
        json.setName(xml.getText("setName"));
        json.setPhone_number(xml.getText("setPhone_number"));
        json.setAddress(xml.getText("setAddress"));
        json.setWebsite(xml.getText("setWebsite"));
        json.setLanguage(xml.getText("setLanguage"));

        List<String> im = new ArrayList<String>();
        String[] TypesList = xml.getText("setTypes").split(",");
        int TypesListCount = xml.getText("setTypes").split(",").length;
        for(int i=0;i<TypesListCount;i++)
        {
            im.add(TypesList[i]);
        }
        json.setTypes(im);

        Location l = new Location();
        l.setLat(Double.parseDouble(xml.getText("setLat")));
        l.setLng(Double.parseDouble(xml.getText("setLng")));
        json.setLocation(l);
    }

    public void printJSON()
    {
        JsonSerializer j = JsonSerializer.DEFAULT_READABLE;
        String printJSON = j.serialize(json);
        System.out.println(printJSON);
        test.pass("Input Body - "+printJSON);
    }

    public void placeIDResponse() throws IOException {

        RequestSpecification req1 =
                new RequestSpecBuilder().
                        setBaseUri(td.getProp("BaseURI")).
                        setContentType(ContentType.JSON).
                        addQueryParam("key", "qaclick123").build();

        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).
                expectBody("scope", equalTo("APP")).build();

        RequestSpecification req = given().relaxedHTTPSValidation().spec(req1).body(json);

        resp  = req.
                when().post("/maps/api/place/add/json").
                then().spec(res).extract().response();
    }

    public void getPlaceID()
    {
        placeID jsonOP = resp.as(placeID.class);
        String id = jsonOP.getPlace_id();
        System.out.println("successfull Place_id - " +id);
        test.pass("Place ID - "+id);
    }
}
