package controllers;

import code.Server;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class GeneralController {

    Server server = new Server();

    @FXML
    private Text fioField;

    @FXML
    private Text sexField;

    @FXML
    private Text schoolField;

    @FXML
    private Text classNameField;

    @FXML
    void initialize() {
        assert fioField != null : "fx:id=\"fioField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert sexField != null : "fx:id=\"sexField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert schoolField != null : "fx:id=\"schoolField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert classNameField != null : "fx:id=\"classNameField\" was not injected: check your FXML file 'general_info.fxml'.";

        JsonObject jObj = null;
        try {
            jObj = server.getPersonData();
            Set<Map.Entry<String, JsonElement>> entries = jObj.entrySet();// test
            for (Map.Entry<String, JsonElement> entry : entries)// test
                System.out.println(entry.getKey() + " " + entry.getValue());// test
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jObj != null) {
            fioField.setText(jObj.get("user_fullname").getAsString());
            sexField.setText(jObj.get("selected_pupil_is_male").getAsBoolean() ?
                    "Мальчик" :
                    "Девочка");
            schoolField.setText(jObj.get("selected_pupil_school").getAsString());
            classNameField.setText(jObj.get("selected_pupil_classyear").getAsString());
        }
    }
}
