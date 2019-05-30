package com.apitesting;

import org.testng.annotations.DataProvider;

public class TestData {

    public static String currLocation = "Vilnius";
    public static int statusCode200 = 200;
    public static int statusCode400 = 400;
    public static int statusCode401 = 401;
    public static int statusCode404 = 404;

    public static String invalidApiKey = "212884f7595eafbcc4e0fe88e5b7318w";


    @DataProvider(name = "IDLocations")
    public String[][] IDLocations() {
        return new String[][] {
                {"593116","Vilnius"},       // location_id, location
                {"598316","Kaunas"},
                {"598098","Klaipeda"},
                {"598098","Siauliai"},      //intentionally fail test
                {"598098","LosAngeles"},    //intentionally fail test
        };
    }

    @DataProvider(name = "LocationCoordinates")
    public String[][] LocationCoordinates() {
        return new String[][] {
                {"55.501851","25.605088"},  // latitude, longtitude
                {"40.501","30.605"},
                {"10.5018515858","77.605585858088"},
                {"55501851","25.605088"}, //intentionally fail test
                {"55.501851","25605088"}, //intentionally fail test
        };
    }

    @DataProvider(name = "Locations")
    public String[] Locations() {
        return new String[] {
                "Vilnius",
                "Kaunas",
                "Klaipeda",
                "Siauliai",
                "LosAngeles",    //intentionally fail test
        };
    }

    @DataProvider(name = "Modes")
    public String[] Modes() {
        return new String[] {
                "json",
                "xml",
                "html",
        };
    }

    @DataProvider(name = "Units")
    public String[] Units() {
        return new String[] {
                "imperial",
                "metric",
                "",
        };
    }



}
