package com.target.case_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ExternalConnectionService {

    @Autowired
    public ParsingService parsingService;

    public String url;

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Queries a passed in url
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String getPayload(final String url) throws IOException{
        HttpURLConnection connection = null;

        try {
            URL connectionUrl = new URL(url);

            connection = (HttpURLConnection) connectionUrl.openConnection();

            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader bufferedReader = setBufferedReader(connection);

            if (connection.getResponseCode() == 200) {
                String responseLine = null;
                while ((responseLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(responseLine + "\n");
                }
            }

            return stringBuilder.toString();

        } finally {
         if (connection != null) {
             connection.disconnect();
         }
        }

    }

    /**
     * Sets either input stream or error stream depending on success of query
     *
     * @param connection
     * @return
     */
    private static BufferedReader setBufferedReader(HttpURLConnection connection) {
        InputStreamReader inputStreamReader = null;

        try {
            if (connection.getResponseCode() == 200) {
                inputStreamReader = new InputStreamReader(connection.getInputStream());
            }
             else if (connection.getErrorStream() != null) {
                 inputStreamReader = new InputStreamReader(connection.getErrorStream());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new BufferedReader(inputStreamReader);
    }

}
