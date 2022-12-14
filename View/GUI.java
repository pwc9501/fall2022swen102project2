package View;

import Controller.OpenUserInput;
import Controller.ScheduleText;
import Controller.PopUp;
import Controller.RandomNameField;
import Controller.RandomMessageField;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The GUI file implements the user interfaces of our fake texting app. This
 * file specifically implements the main user interface, the sub menu, and the scheduler menu.
 * 
 * @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 * 
 */

public class GUI extends Application{
    
    /**
     * Factory Method: makes and returns a button for the user interface
     * 
     * @param text - text for the button to display on the user interface
    */
    private Button makeButton(String text){
        Button b = new Button(text);
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b.setTextAlignment(TextAlignment.CENTER);
        b.setTextFill(Color.WHITE);
        b.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY)));
        return b;
    }

    /**
     * Factory Method: makes and returns a Text Field for the user interface
     * 
     * @param text - text for the text field to display on the user interface
    */
    private TextField makeTextField(String text){
        TextField t = new TextField();
        t.setPromptText(text);
        t.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return t;
    }

    /**
     * makes and returns a ComboBox that determines what speech bubble the user wants
     * to use when sending the text
    */
    private ComboBox<String> addTextBubbleType(){
        ComboBox<String> TextBubbleType = new ComboBox<>();
        TextBubbleType.getItems().add("Sender Message");
        TextBubbleType.getItems().add("Reciever Message");
        TextBubbleType.getSelectionModel().selectFirst();
        TextBubbleType.setMaxWidth(Double.MAX_VALUE);
        return TextBubbleType;
    }


     /**
     * Adds integers (corresponding to hours) as items in the combo box
     */
    public ComboBox<Integer> addHoursDropDown() {
        ComboBox<Integer> cboxHours = new ComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cboxHours.getItems().add(i);
        }
        return cboxHours;
    }

    /**
     * Adds the colon to seperate the hour and minute comboboxs
     */
    public Label addColon() {
        Label lblColon = new Label(":");
        lblColon.setFont(new Font(STYLESHEET_CASPIAN, 22));
        lblColon.setTextFill(Color.WHITE);
        return lblColon;
    }

    /**
     * Adds integers (corresponding to minutes) as items in the combo box
     */
    public ComboBox<String> addMinutesDropDown() {
        ComboBox<String> cboxMinutes = new ComboBox<>();
        for (int i = 1; i <= 59; i++) {
            if (i < 10) {
                cboxMinutes.getItems().add("0" + String.valueOf(i));
            }
            else {
                cboxMinutes.getItems().add(String.valueOf(i));
            }
        }
        return cboxMinutes;
    }

    /**
     * Adds Combobox with AM/PM items
     */
    public ComboBox<String> addAmPmDropDown() {
        ComboBox<String> cboxAmPm = new ComboBox<>();
        cboxAmPm.getItems().addAll("AM", "PM");
        return cboxAmPm;
    }
    
    @Override
    public void start(Stage stage){
            BorderPane borderPane = new BorderPane();
            borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            //title and sender label
            VBox beginningBox = new VBox();
            beginningBox.setAlignment(Pos.CENTER);

            String content = "Ritche's Texts";
            Text title = new Text();
            title.setFont(Font.font("Impact", FontWeight.BOLD, 25));
            title.setFill(Color.ORANGE);
            title.setTextAlignment(TextAlignment.CENTER);

            beginningBox.getChildren().add(title);

            Label nameLabel = new Label("");
            nameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            nameLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
            nameLabel.setTextFill(Color.WHITE);
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setFont(new Font(20));
            nameLabel.setPadding(new Insets(10, 0, 10, 0));

            beginningBox.getChildren().add(nameLabel);

            borderPane.setTop(beginningBox);
            //end of title and sender label
            

            //typing animation for title
            Animation typingAnimation = new Transition(){
                {
                    setCycleDuration(Duration.millis(2000));
                }
            
                protected void interpolate(double frac) {
                    final int length = content.length();
                    final int n = Math.round(length * (float) frac);
                    title.setText(content.substring(0, n));
                }
            };

            typingAnimation.play();

            
            //scrollpane
            ScrollPane scroll = new ScrollPane();
            VBox text = new VBox();
            scroll.setPrefHeight (200) ;
            scroll.setPrefWidth(350);
            scroll.setFitToHeight(true);
            scroll.setFitToWidth(true);
            scroll.setContent(text);
            scroll.setStyle("-fx-background: #000000; -fx-border-color: #000000");
            borderPane.setCenter(scroll);
            //end of scrollpane


            //userControls
            HBox userControls = new HBox();
            userControls.setPadding(new Insets(10));
            userControls.setAlignment(Pos.CENTER);
            
            TextField userTextField = makeTextField("Enter Message");
            userControls.getChildren().add(userTextField);
            
            Button userSend =  makeButton("Send Text");
            userControls.getChildren().add(userSend);

            userTextField.setEditable(false);
            userSend.setDisable(true);

            Button popUp =  makeButton("?");
            userControls.getChildren().add(popUp);
            

            borderPane.setBottom(userControls);

            //end of userControls

            //popUpStage

            Stage popUpStage = new Stage();
            popUp.setOnAction(new OpenUserInput(popUpStage));

            GridPane popUpPane = new GridPane();
            popUpPane.setPadding(new Insets(10));
            popUpPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            
            TextField popUpTextField =  makeTextField("Enter Message");
            Button randomText =  makeButton("Random Message");

            TextField popUpSenderField =  makeTextField("Enter Name");
            Button randomSender =  makeButton("Random Name");

            TextField timeSchedule =  makeTextField("Schedule Delivery");
            Button schedule = makeButton("Schedule");

            Text speechBubbleSubTitle = new Text();
            speechBubbleSubTitle.setFont(Font.font("Impact", FontWeight.BOLD, 15));
            speechBubbleSubTitle.setFill(Color.WHITE);
            speechBubbleSubTitle.setText("Choose Speech Bubble");
            ComboBox<String> TextBubbleComboBox = addTextBubbleType();

            Button sendNow =  makeButton("Send");

            sendNow.disableProperty().bind(popUpTextField.textProperty().isEmpty());

            popUpPane.add(popUpTextField, 0, 1);
            popUpPane.add(randomText, 1, 1);

            popUpPane.add(popUpSenderField, 0, 0);
            popUpPane.add(randomSender, 1, 0);

            popUpPane.add(timeSchedule, 0, 4);
            popUpPane.add(schedule, 1, 4);

            popUpPane.add(speechBubbleSubTitle, 0, 2);
            popUpPane.add(TextBubbleComboBox, 0, 3, 2, 1);

            popUpPane.add(sendNow, 0, 5, 2, 1);

            popUpPane.setVgap(10);
            
            EventHandler<ActionEvent> randomTextObserver = new RandomMessageField(popUpTextField);
            randomText.setOnAction(randomTextObserver);

            EventHandler<ActionEvent> randomNameObserver = new RandomNameField(popUpSenderField);
            randomSender.setOnAction(randomNameObserver);

            EventHandler<ActionEvent> updateObserver = new PopUp(timeSchedule, popUpSenderField, popUpTextField, text, randomSender, popUpStage, TextBubbleComboBox, beginningBox, popUp);
            sendNow.setOnAction(updateObserver);
            

            //end of PopUpStage

            //schedule popUp 
            Stage scheduleStage = new Stage();
            schedule.setOnAction(new OpenUserInput(scheduleStage));

            BorderPane schedulePane = new BorderPane();
            schedulePane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
            HBox hbox = new HBox();
            
            ComboBox<Integer> hours = addHoursDropDown();
            ComboBox<String> minutes = addMinutesDropDown();
            ComboBox<String> meridiem = addAmPmDropDown();
            hbox.getChildren().add(hours);
            hbox.getChildren().add(addColon());
            hbox.getChildren().add(minutes);
            hbox.getChildren().add(meridiem);

            schedulePane.setCenter(hbox);
            Button scheduleButton = makeButton("Confirm");

            scheduleButton.disableProperty().bind(hours.valueProperty().isNull().or(minutes.valueProperty().isNull()).or(meridiem.valueProperty().isNull()));

            schedulePane.setBottom(scheduleButton);
            
            EventHandler<ActionEvent> scheduleTextObserver = new ScheduleText(hbox, timeSchedule, popUpStage, scheduleStage);
            scheduleButton.setOnAction(scheduleTextObserver);
            //end of schedule popUp


            Scene scene = new Scene(borderPane, 600, Double.MAX_VALUE);
            scene.getStylesheets().add("design.css");
            stage.setScene(scene);
            stage.setResizable(false);
            popUpStage.setScene(new Scene(popUpPane));
            popUpStage.setTitle("Sub Menu");
            scheduleStage.setScene(new Scene(schedulePane));
            scheduleStage.setTitle("Scheduler Menu");
            stage.setTitle("Ritchie's Texts App");
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
