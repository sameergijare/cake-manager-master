package com.waracle.cakemgr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpClient {

    private SimpleHttpClient() {}

    static InputStream get(String url, String mimeType) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(CakeConstants.GET_REQUEST_METHOD);
        connection.setRequestProperty(CakeConstants.GET_REQUEST_PROPERTY, mimeType);
        return connection.getInputStream();
    }

    static InputStream getJson(String url) throws IOException {
        return get(url, CakeConstants.JSON_CONTENT_TYPE);
    }
}
