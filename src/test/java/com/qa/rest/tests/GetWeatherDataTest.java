package com.qa.rest.tests;

import com.apitesting.*;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


public class GetWeatherDataTest extends BaseClass {

    Messages msg = new Messages();
    CommonParams params = new CommonParams();

    /* Get Weather by coordinates test */
    @Test(dataProvider="LocationCoordinates", dataProviderClass = TestData.class)
     public void verifyGetWeatherByCoordinates(String latitude, String longtitude){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("lat", latitude).
                param("lon", longtitude).
                param("units", "metric").
                param("appid", params.getApiKey()).
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: " + BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+latitude+", "+longtitude);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);    // validate status code 200
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);

        response.contentType(ContentType.JSON);         // validate JSON type
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.jsonOkMsg);

        response.body("name", Matchers.notNullValue());     // validate name not null
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.nameOkMsg);

        response.body("id", Matchers.notNullValue());     // validate id not null
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.idOkMsg);

    }

    /* Location : ID match test */
    @Test(dataProvider = "IDLocations", dataProviderClass = TestData.class)
    public void verifyIdLocationMatch(String location_id, String location){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                    param("id", location_id).
                    param("appid", params.getApiKey()).
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+location+", "+location_id);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);    // validate status code 200
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);

        response.contentType(ContentType.JSON);         // validate JSON type
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.jsonOkMsg);

        response.body("name", Matchers.notNullValue());     // validate name not null
        response.body("name", Matchers.equalToIgnoringCase(location));  // validate name
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.locationNameOkMsg);

        response.body("id", Matchers.notNullValue());
        response.body("id", Matchers.equalTo(Integer.parseInt(location_id)));   // validate ID
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.locationIdOkMsg);


    }

    /* Validate JSON response body structure and data */
    @Test(dataProvider = "Locations", dataProviderClass = TestData.class)
    public void validateJSONResponseBody(String location){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("q", location).
                param("appid", params.getApiKey()).
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+location);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);    // validate status code 200
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);

        response.contentType(ContentType.JSON);         // validate JSON type
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.jsonOkMsg);

        // Check whole body structure and data not Null
        CommonMethods.verifyJsonResponseStructure(response);

    }

    /* Validate XML response */
    @Test(dataProvider = "Locations", dataProviderClass = TestData.class)
    public void validateXMLResponse(String location){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("q", location).
                param("appid", params.getApiKey()).
                param("mode", "xml").
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+location);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);

        response.contentType(ContentType.XML);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.XMLOkMsg);

    }

    /* Validate HTML response */
    @Test(dataProvider = "Locations", dataProviderClass = TestData.class)
    public void validateHTMLResponse(String location){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("q", location).
                param("appid", params.getApiKey()).
                param("mode", "html").
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+location);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);

        response.contentType(ContentType.HTML);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.HTMLOkMsg);

    }

     /* Test with invalid api key in various modes */
    @Test(dataProvider = "Modes", dataProviderClass = TestData.class)
    public void invalidApiKey(String mode){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("q", TestData.currLocation).
                param("appid", TestData.invalidApiKey).
                param("mode", mode).
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+mode);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode401);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat401okMsg);

        response.body("message", Matchers.containsStringIgnoringCase("Invalid API key"));
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.invalidAPIkeyMsg);

    }

    /* Test various measurement units */
    @Test(dataProvider = "Units", dataProviderClass = TestData.class)
    public void MeasurementUnits(String units){

        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("q", TestData.currLocation).
                param("appid", params.getApiKey()).
                param("units", units).
                when()
                .get().then();

        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Data: "+units);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode200);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat200okMsg);
    }

    /* verify 400 (Bad Request) Status */
    @Test
    public void verify400status(){
        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("appid", params.getApiKey()).
                when()
                .get().then();
        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode400);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat400okMsg);
    }

    /* verify 404 (Not Found) Status */
    @Test
    public void verify404status(){
        RestAssured.baseURI = params.getBaseURI();
        ValidatableResponse response = RestAssured.
                given().
                param("id", "7878").
                param("appid", params.getApiKey()).
                when()
                .get().then();
        String readableResponse = response.extract().asString();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case: "+BaseClass.getMethodName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Response : " + readableResponse);

        response.statusCode(TestData.statusCode404);
        ExtentTestManager.getTest().log(LogStatus.INFO, msg.stat404okMsg);
    }


}
