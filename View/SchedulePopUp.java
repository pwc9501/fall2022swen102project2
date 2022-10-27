package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SchedulePopUp extends Application {
    private HBox hbox;
    private BorderPane borderPane;

    /**
     * Adds integers (corresponding to hours) as items in the combo box
     */
    public void addHoursDropDown() {
        ComboBox<Integer> cboxHours = new ComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cboxHours.getItems().add(i);
        }
        hbox.getChildren().add(cboxHours);
    }

    /**
     * Adds the colon to seperate the hour and minute comboboxs
     */
    public void addColon() {
        Label lblColon = new Label(":");
        lblColon.setFont(new Font(STYLESHEET_CASPIAN, 22));
        lblColon.setTextFill(Color.WHITE);
        hbox.getChildren().add(lblColon);
    }


    /**
     * Adds integers (corresponding to minutes) as items in the combo box
     */
    public void addMinutesDropDown() {
        ComboBox<Integer> cboxMinutes = new ComboBox<>();
        for (int i = 1; i <= 59; i++) {
            cboxMinutes.getItems().add(i);
        }
        hbox.getChildren().add(cboxMinutes);
    }


    /**
     * Adds Combobox with AM/PM items
     */
    public void addAmPmDropDown() {
        ComboBox<String> cboxAmPm = new ComboBox<>();
        cboxAmPm.getItems().addAll("AM", "PM");
        hbox.getChildren().add(cboxAmPm);
    }



    /**
     * Adds Confirm button
     */
    public void addConfirmButton() {
        Button btnConfirm = new Button("Confirm");
        btnConfirm.setAlignment(Pos.CENTER);
        btnConfirm.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnConfirm.setTextAlignment(TextAlignment.CENTER);
        btnConfirm.setTextFill(Color.WHITE);
        btnConfirm.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY)));
        borderPane.setBottom(btnConfirm);

        
    }


    @Override
    public void start(Stage stage) throws Exception {
        borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
        stage.setScene(new Scene(borderPane));
        stage.setTitle("Schedule pop up");
        hbox = new HBox();

        addHoursDropDown();
        addColon();
        addMinutesDropDown();
        addAmPmDropDown();
        addConfirmButton();
        
       

        
        borderPane.setCenter(hbox);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
