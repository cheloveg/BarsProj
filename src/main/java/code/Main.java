package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
//        System.out.println(server.cookies);
        launch(args);
    }
}
