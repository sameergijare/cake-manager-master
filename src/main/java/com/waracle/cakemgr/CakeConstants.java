package com.waracle.cakemgr;

public class CakeConstants {

    private CakeConstants() {}

    public static final String HOME_URL_PATTERN = "";
    public static final String CAKES_URL_PATTERN = "/cakes?token=abcd";

    public static final String JSON_CONTENT_TYPE = "application/json";

    public static final String HTML_REDIRECT_SUFFIX = "/cakes.html";

    public static final String GET_REQUEST_METHOD = "GET";
    public static final String GET_REQUEST_PROPERTY = "Accept";

    public static final String TITLE_PARAM = "title";
    public static final String DESCRIPTION_PARAM = "description";
    public static final String IMAGE_PARAM = "image";

    public static final String INITIAL_CAKE_DATA_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
}
