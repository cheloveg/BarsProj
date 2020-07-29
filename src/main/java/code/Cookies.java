package code;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class Cookies {
    private static final String COOKIES_HEADER = "Set-Cookie";
    private static CookieManager cookieManager = new CookieManager();

    public void catchCookies(HttpURLConnection connection) {
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
        }
    }

    public void imitationCatchCookies(String[] cookies){
        for (String cookie:
             cookies) {
            cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
        }
    }

    public void uploadCookies(HttpURLConnection connection){
        if (cookieManager.getCookieStore().getCookies().size() > 0) {
            connection.setRequestProperty("Cookie", this.toString());
        }
    }

    @Override
    public String toString() {
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        StringBuilder cookiesStr = new StringBuilder();
        for (int i = 0; i < cookies.size(); i++) {
            if (i != 0) cookiesStr.append("; ");
            cookiesStr.append(cookies.get(i).toString());
        }
        return cookiesStr.toString();
    }
}
