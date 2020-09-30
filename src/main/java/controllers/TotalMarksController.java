package controllers;


import code.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class TotalMarksController {

    Server server = new Server();

    @FXML
    private DatePicker toTotalMarksDate;

    @FXML
    private Button showTotalMarksButton;

    @FXML
    private Text TotalMarksText;

    @FXML
    private DatePicker fromTotalMarksDate;

    @FXML
    void showTotalMarksClick(MouseEvent event) throws IOException {
        System.out.println(server.getTotalMarks(
                fromTotalMarksDate.getChronology().toString(), toTotalMarksDate.getChronology().toString()
        ));
    }

    @FXML
    void initialize() {
        assert toTotalMarksDate != null : "fx:id=\"toTotalMarksDate\" was not injected: check your FXML file 'total_marks.fxml'.";
        assert showTotalMarksButton != null : "fx:id=\"showTotalMarksButton\" was not injected: check your FXML file 'total_marks.fxml'.";
        assert TotalMarksText != null : "fx:id=\"TotalMarksText\" was not injected: check your FXML file 'total_marks.fxml'.";
        assert fromTotalMarksDate != null : "fx:id=\"fromTotalMarksDate\" was not injected: check your FXML file 'total_marks.fxml'.";

    }
}
