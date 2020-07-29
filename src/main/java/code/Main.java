package code;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Map;
import java.util.Set;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/sample.fxml"));
        primaryStage.setTitle("Bars Proj");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
//        code.Server server = new code.Server();
//        server.authorize("06_uch_01", "yYVZ6UuX");
//        server.startImitation(new String[]{"NodeID=node2;", "sessionid=wqn9eeq0iehguv5152y9s42zn550az9u"});
//        JsonObject jObj = server.getPersonData();// test
//
//        Set<Map.Entry<String, JsonElement>> entries = jObj.entrySet();// test
//        for (Map.Entry<String, JsonElement> entry : entries)// test
//            System.out.println(entry.getKey() + " " + entry.getValue());// test

// Работа с массивом JSON
//        System.out.println(jObj.getAsJsonArray("indicators").get(0).getAsJsonObject().get("value"));
// Работа с членом JSON
//        System.out.println(jObj.get("user_fullname").getAsString());
//        System.out.println(server.cookies);
        launch(args);
    }
}
