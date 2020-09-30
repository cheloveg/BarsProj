package controllers;

import code.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class GeneralMenuController {


    @FXML
    private Button toGeneralInfoButton;

    @FXML
    private Button toDailyMarksButton;

    @FXML
    private Button notUsingButton;

    @FXML
    void toDailyMarksClick(MouseEvent event) {

    }

    @FXML
    void toGeneralInfoClick(MouseEvent event) throws IOException {
        Transition.hideWindow(toGeneralInfoButton);
        URL layout = getClass().getResource("/layouts/general_diary.fxml");
        Transition.openWindow(layout, true);
    }

    @FXML
    void initialize() {
        assert toGeneralInfoButton != null : "fx:id=\"toGeneralInfoButton\" was not injected: check your FXML file 'general_menu.fxml'.";
        assert toDailyMarksButton != null : "fx:id=\"toDailyMarksButton\" was not injected: check your FXML file 'general_menu.fxml'.";
        assert notUsingButton != null : "fx:id=\"notUsingButton\" was not injected: check your FXML file 'general_menu.fxml'.";

    }
}
