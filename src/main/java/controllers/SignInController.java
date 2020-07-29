package controllers;

import code.Server;
import code.Transition;
import code.Verificator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SignInController {

    Server server = new Server();

    @FXML
    private AnchorPane form;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInBt;

    @FXML
    void signInBtClick(MouseEvent event) throws IOException {
        if (Verificator.checkFillTextField(new TextField[]{
                loginField, passwordField
        }) && server.checkAuthorize(loginField.getText(), passwordField.getText())) {
            Transition.hideWindow(signInBt);
            URL layout = getClass().getResource("/layouts/general_info.fxml");
            Transition.openWindow(layout, true);
        }
    }

    @FXML
    void initialize() {
        assert form != null : "fx:id=\"form\" was not injected: check your FXML file 'sample.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'sample.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'sample.fxml'.";
        assert signInBt != null : "fx:id=\"signInBt\" was not injected: check your FXML file 'sample.fxml'.";


    }
}
