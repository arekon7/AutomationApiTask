package com.apitesting;

public class CommonParams {

    private static String BaseURI = "http://api.openweathermap.org/data/2.5/weather?";
    private static String ApiKey = "212884f7595eafbcc4e0fe88e5b7318e";
    private static String[] bodyUnits = new String[]{
            "coord",
            "coord.lon",
            "coord.lat",
            "weather",
            "weather.id",
            "weather.main",
            "weather.description",
            "weather.icon",
            "base",
            "main",
            "main.temp",
            "main.pressure",
            "main.humidity",
            "main.temp_min",
            "main.temp_max",
            "visibility",
            "wind",
            "wind.speed",
            "clouds",
            "clouds.all",
            "dt",
            "sys",
            "sys.type",
            "sys.id",
            "sys.message",
            "sys.country",
            "sys.sunrise",
            "sys.sunset",
            "timezone",
            "id",
            "name",
            "cod",

    };

    public String getBaseURI(){
        return BaseURI;
    }

    public String getApiKey(){
        return ApiKey;
    }

    public static String[] getBodyUnits(){
        return bodyUnits;
    }







}
