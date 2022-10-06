import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gui extends Application{




    @Override
    public void start(Stage stage){
            Button b = new Button();
            stage.setScene(new Scene(b));
            stage.setTitle("Template");
            stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}