package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GeneralNewDiary {

    @FXML
    private AnchorPane generalNewDiary;

    @FXML
    private Button Monday;

    @FXML
    private Button Tuesday;

    @FXML
    private Button Wednesday;

    @FXML
    private Button Thursday;

    @FXML
    private Button Friday;

    @FXML
    private Button Saturday;

    @FXML
    private Button Sunday;

    public void start(){
        generalNewDiary.getChildren().add(Monday);
        generalNewDiary.getChildren().add(Tuesday);
        generalNewDiary.getChildren().add(Wednesday);
        generalNewDiary.getChildren().add(Thursday);
        generalNewDiary.getChildren().add(Friday);
        generalNewDiary.getChildren().add(Saturday);

        int dayWidth = (int)generalNewDiary.getWidth()/7;
        int dayHeight = (int)generalNewDiary.getHeight()/7;

        Monday.setPrefWidth(dayWidth);
        Tuesday.setPrefWidth(dayHeight);
    }


    public void setDaysSize(){

    }
}
