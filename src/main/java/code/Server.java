package code;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    private String basicUrl = "https://schools48.ru/";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36";
    Cookies cookies;

    private String paramsConvertToLine(Map<String, String> params){
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()){
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), Charset.forName("UTF-8")));
            postData.append('=');
            postData.append(URLEncoder.encode(param.getValue(), Charset.forName("UTF-8")));
        }
        return postData.toString();
    }

    private HttpURLConnection post(String url, Map<String, String> params) throws IOException {
        URL obj = new URL(basicUrl + url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept", "*/*");

        if (params != null && params.size() != 0){
            con.setDoOutput(true);
            try(OutputStream os = con.getOutputStream()){
                byte[] input = paramsConvertToLine(params).getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }
        }

        cookies = new Cookies(con);

        int responseCode = con.getResponseCode();
        System.out.println("Sending POST to URL:" + url);
        System.out.println("Response code: " + responseCode);

        return con;
    }

    private JsonObject getJsonResponse(HttpURLConnection connection) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null)
                response.append(responseLine.trim());

            return JsonParser.parseString(response.toString()).getAsJsonObject();
        }
    }

    public ArrayList<String> authorize(String login, String password) throws IOException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("login_login", login);
        params.put("login_password", password);

        HttpURLConnection con = this.post("auth/login", params);

        JsonObject jObj = getJsonResponse(con);
        Set<Map.Entry<String, JsonElement>> entries = jObj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries)
            System.out.println(entry.getKey() + " " + entry.getValue());


        return null;
    }
}
