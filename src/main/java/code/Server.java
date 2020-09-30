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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    private String basicUrl = "https://schools48.ru/";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36";
    static Cookies cookies = new Cookies();

    private String paramsConvertToLine(Map<String, String> params) {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
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

        cookies.uploadCookies(con);

        if (params != null && params.size() != 0) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = paramsConvertToLine(params).getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }
        }

        cookies.catchCookies(con);

        int responseCode = con.getResponseCode();
        if (responseCode != 200){
            System.out.println("Ошибка авторизации! Код Http: "+responseCode+"; ");
            throw new IOException();
        }
//        System.out.println("Sending POST to URL:" + url);
//        System.out.println("Response code: " + responseCode);

        return con;
    }

    public HttpURLConnection get(String url, Map<String, String> params) throws IOException {
        URL obj;
        if (params != null && params.size() != 0) {
            obj = new URL(basicUrl + url + "?" + paramsConvertToLine(params));
        } else
            obj = new URL(basicUrl + url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept", "*/*");

        cookies.uploadCookies(con);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        cookies.catchCookies(con);

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

    public boolean checkAuthorize(String login, String password){
        JsonObject jObj = null;
        try {
            jObj = authorize(login, password);
            if (jObj != null && jObj.get("success").getAsBoolean()){
                this.get("/", null);
                return true;
            } else // TODO обработать бан
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JsonObject authorize(String login, String password) throws IOException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("login_login", login);
        params.put("login_password", password);

        HttpURLConnection con = this.post("auth/login", params);

        System.out.println(cookies.toString());

        return getJsonResponse(con); //release

//        //-----// test //-----//
//        JsonObject jObj = getJsonResponse(con);// test
//        Set<Map.Entry<String, JsonElement>> entries = jObj.entrySet();// test
//        for (Map.Entry<String, JsonElement> entry : entries)// test
//            System.out.println(entry.getKey() + " " + entry.getValue());// test
//        return jObj; // test
    }

    public JsonObject getPersonData() throws IOException {
        HttpURLConnection con = this.get("api/ProfileService/GetPersonData", null);
        return getJsonResponse(con);
    }

    public JsonObject getTotalMarks(String dateFrom, String dateTo) throws IOException{
        Map<String, String> params = new LinkedHashMap<>();
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);

        HttpURLConnection con = this.get("apt/MarkService/GetTotalMarks", params);

        return getJsonResponse(con);
    }

    public JsonObject getDiary(String date) throws IOException{
        Map<String, String> params = new LinkedHashMap<>();
        params.put("date", date);
        params.put("is_diary", "true");

        HttpURLConnection con = this.get("apt/ScheduleService/GetDiary", params);

        return getJsonResponse(con);
    }

    public void startImitation(String[] cookies) {
        this.cookies.imitationCatchCookies(cookies);
    }
}
