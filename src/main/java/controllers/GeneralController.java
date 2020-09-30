package controllers;

import code.Server;
import code.Transition;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

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
    private Button backGenMenuButton;

    @FXML
    void backGenMenuClick(MouseEvent event) throws IOException {
        Transition.hideWindow(backGenMenuButton);
        URL layout = getClass().getResource("/layouts/general_menu.fxml");
        Transition.openWindow(layout, true);
    }

    @FXML
    void initialize() {
        assert fioField != null : "fx:id=\"fioField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert sexField != null : "fx:id=\"sexField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert schoolField != null : "fx:id=\"schoolField\" was not injected: check your FXML file 'general_info.fxml'.";
        assert classNameField != null : "fx:id=\"classNameField\" was not injected: check your FXML file 'general_info.fxml'.";

        JsonObject jObj = null;
        try {
            jObj = server.getPersonData();
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
