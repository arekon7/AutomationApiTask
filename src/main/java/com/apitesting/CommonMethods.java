package com.apitesting;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

public class CommonMethods {

    /* Check Response structure and data Not Null */
    public static void verifyJsonResponseStructure(ValidatableResponse response){
        ExtentTestManager.getTest().log(LogStatus.INFO, Messages.bodyStructureVerification);
        for (String bodyUnit : CommonParams.getBodyUnits()) {
            response.body(bodyUnit, Matchers.notNullValue());
            ExtentTestManager.getTest().log(LogStatus.INFO, bodyUnit + " OK");
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, Messages.bodyStructureOk);
    }




}
