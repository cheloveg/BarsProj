package code;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class Cookies {
    private ArrayList<String> listOfCookies = new ArrayList<>();

    public Cookies(HttpURLConnection connection) {
        catchCookies(connection);
    }

    private void catchCookies(HttpURLConnection connection) {
        for (int i = 0; ; i++) {
            String headerName = connection.getHeaderField(i);
            String headerValue = connection.getHeaderFieldKey(i);

            if (headerName != null && headerValue != null) {
                if ("Set-Cookie".equalsIgnoreCase(headerName)) {
                    String[] fields = headerValue.split(";\\s");
                    listOfCookies.addAll(Arrays.asList(fields));
                }
            } else
                break;
        }
    }

    @Override
    public String toString() {
        return listOfCookies.toString();
    }
}
