package geekbrains.Lesson6_JavaCore_GB_Net;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.*;
import java.util.Properties;

public class Lesson6_Net {

    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        loadProperties();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(prop.getProperty("BASE_HOST"))
                .addPathSegment(prop.getProperty("API_VERSION"))
                .addPathSegment(prop.getProperty("FORECAST"))
                .addQueryParameter("lat", prop.getProperty("LAT"))
                .addQueryParameter("lon", prop.getProperty("LON"))
                .addQueryParameter("lang", prop.getProperty("LANG"))
                .addQueryParameter("limit", prop.getProperty("LIMIT"))
                .addQueryParameter("hours", prop.getProperty("HOURS"))
                .addQueryParameter("extra", prop.getProperty("EXTRA"))
                .addQueryParameter("extra", prop.getProperty("EXTRA"))

                .build();

        System.out.println(url.toString());

        Request requestToYandex = new Request.Builder()
                .addHeader("accept", "application/json")
                .addHeader("X-Yandex-API-Key", prop.getProperty("API_KEY"))
                .url(url)
                .get()
                .build();

        String jsonResponse = client.newCall(requestToYandex).execute().body().string();
        System.out.println(jsonResponse);

    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/resources/config.properties")){
            prop.load(configFile);
        }
    }

}
