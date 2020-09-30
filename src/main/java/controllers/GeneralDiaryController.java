package controllers;

import code.Server;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GeneralDiaryController {

    Server server = new Server();
    JsonObject jObj = null;

    @FXML
    private DatePicker generalDiaryDate;

    @FXML
    private AnchorPane generalDiaryMon;

    @FXML
    private AnchorPane generalDiaryTus;

    @FXML
    private AnchorPane generalDiaryWed;

    @FXML
    private AnchorPane generalDiaryThu;

    @FXML
    private AnchorPane generalDiaryFri;

    @FXML
    void generalDiaryDateAct(ActionEvent event) {
        try {
            jObj = server.getDiary(generalDiaryDate.getValue().toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        if (jObj != null){

        }
    }

    @FXML
    void initialize() throws IOException {
        assert generalDiaryDate != null : "fx:id=\"generalDiaryDate\" was not injected: check your FXML file 'general_diary.fxml'.";
        assert generalDiaryMon != null : "fx:id=\"generalDiaryMon\" was not injected: check your FXML file 'general_diary.fxml'.";
        assert generalDiaryTus != null : "fx:id=\"generalDiaryTus\" was not injected: check your FXML file 'general_diary.fxml'.";
        assert generalDiaryWed != null : "fx:id=\"generalDiaryWed\" was not injected: check your FXML file 'general_diary.fxml'.";
        assert generalDiaryThu != null : "fx:id=\"generalDiaryThu\" was not injected: check your FXML file 'general_diary.fxml'.";
        assert generalDiaryFri != null : "fx:id=\"generalDiaryFri\" was not injected: check your FXML file 'general_diary.fxml'.";

        ObservableList<String> langs = FXCollections.observableArrayList();
        jObj = server.getDiary(generalDiaryDate.getValue().toString());

        ListView<String> langsListView = new ListView<>(langs);
        langsListView.setMaxSize(200.0, 700.0);
        generalDiaryMon.getChildren().add(langsListView);
    }
}
