package View;

import Controller.OpenUserInput;
import Controller.ScheduleText;
import Controller.popUpTextHandler;
import Random.randomName;
import Random.randomText;
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

public class GUI extends Application{
    
    private Button makeButton(String text){
        Button b = new Button(text);
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b.setTextAlignment(TextAlignment.CENTER);
        b.setTextFill(Color.WHITE);
        b.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY)));
        return b;
    }

    private TextField makeTextField(String text){
        TextField t = new TextField();
        t.setPromptText(text);
        t.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return t;
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
    public ComboBox<Integer> addMinutesDropDown() {
        ComboBox<Integer> cboxMinutes = new ComboBox<>();
        for (int i = 1; i <= 59; i++) {
            cboxMinutes.getItems().add(i);
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

            String content = "Ritche's Texts";
            Text title = new Text();
            title.setFont(Font.font("Impact", FontWeight.BOLD, 20));
            title.setFill(Color.ORANGE);
            title.setTextAlignment(TextAlignment.CENTER);

            borderPane.setTop(title);
         
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

            
            /*
            VBox text = new VBox();
            text.setPadding(new Insets(10));
            for(int i = 0; i < 10; i++){
                Label l = makeLabel();
                text.getChildren().add(l);
                if(i != 9){
                    text.setMargin(l, new Insets(0, 0, 5, 0));
                }
            }
            */
            
            ScrollPane scroll = new ScrollPane();
            VBox text = new VBox();
            scroll.setPrefHeight (200) ;
            scroll.setPrefWidth(350);
            scroll.setFitToHeight(true);
            scroll.setFitToWidth(true);
            scroll.setContent(text);
            scroll.setStyle("-fx-background: #000000; -fx-border-color: #000000");
            borderPane.setCenter(scroll);
            
            HBox userControls = new HBox();
            userControls.setPadding(new Insets(10));
            
            TextField userTextField = makeTextField("Enter text");
            userControls.getChildren().add(userTextField);
            
            Button userSend =  makeButton("Send Text");
            userControls.getChildren().add(userSend);

            Button popUp =  makeButton("?");
            userControls.getChildren().add(popUp);
            
            Stage popUpStage = new Stage();
            popUp.setOnAction(new OpenUserInput(popUpStage));

            //popUpStage

            GridPane popUpPane = new GridPane();
            popUpPane.setPadding(new Insets(10));
            popUpPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            
            TextField popUpTextField =  makeTextField("Enter text");
            Button randomText =  makeButton("Random Text");

            TextField popUpSenderField =  makeTextField("Enter Name");
            Button randomSender =  makeButton("Random Name");

            TextField timeSchedule =  makeTextField("schedule the time");
            Button schedule = makeButton("Schedule");

            Button sendNow =  makeButton("Send");


            popUpPane.add(popUpTextField, 0, 1);
            popUpPane.add(randomText, 1, 1);

            popUpPane.add(popUpSenderField, 0, 0);
            popUpPane.add(randomSender, 1, 0);

            popUpPane.add(timeSchedule, 0, 2);
            popUpPane.add(schedule, 1, 2);

            popUpPane.add(sendNow, 0, 3);
            
            EventHandler<ActionEvent> randomTextObserver = new randomTextField(popUpTextField);
            randomText.setOnAction(randomTextObserver);

            EventHandler<ActionEvent> randomNameObserver = new randomNameField(popUpSenderField);
            randomSender.setOnAction(randomNameObserver);

            EventHandler<ActionEvent> updateObserver = new popUpTextHandler(timeSchedule, popUpSenderField, popUpTextField, text, randomSender, popUpStage);
            sendNow.setOnAction(updateObserver);
            
            EventHandler<ActionEvent> sendObserver = new SendMessageHandler(text, userTextField, scroll);
            userSend.setOnAction(sendObserver) ;

            //schedule popUp 
            Stage scheduleStage = new Stage();
            schedule.setOnAction(new OpenUserInput(scheduleStage));

            BorderPane schedulePane = new BorderPane();
            schedulePane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
            HBox hbox = new HBox();
    
            hbox.getChildren().add(addHoursDropDown());
            hbox.getChildren().add(addColon());
            hbox.getChildren().add(addMinutesDropDown());
            hbox.getChildren().add(addAmPmDropDown());
            schedulePane.setCenter(hbox);
            Button scheduleButton = makeButton("Confirm");
            schedulePane.setBottom(scheduleButton);
            
            EventHandler<ActionEvent> scheduleTextObserver = new ScheduleText(hbox, timeSchedule, popUpStage, scheduleStage);
            scheduleButton.setOnAction(scheduleTextObserver);
            

            borderPane.setBottom(userControls);

            Scene scene = new Scene(borderPane, 400, Double.MAX_VALUE);
            scene.getStylesheets().add("design.css");
            stage.setScene(scene);
            popUpStage.setScene(new Scene(popUpPane));
            scheduleStage.setScene(new Scene(schedulePane));
            stage.setTitle("Fake Text App");
            stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
/*
class UpdateHandler implements EventHandler<ActionEvent>{
    private VBox v;
    private TextField text;

    public UpdateHandler(VBox v, TextField text){
        this.v = v;
        this.text = text;
    }

    @Override
    public void handle(ActionEvent arg0) {
        String[] messages = new String[v.getChildren().size()];
        for(int i = 0; i < v.getChildren().size(); i++){
            messages[i] = ((Label) v.getChildren().get(i)).getText();
            ((Label) v.getChildren().get(i)).setText("");
        }
        ((Label) v.getChildren().get(0)).setText(text.getText());
        ((Label) v.getChildren().get(0)).setFont(new Font(20));
        for(int i = 1; i < v.getChildren().size(); i++){
            ((Label) v.getChildren().get(i)).setText(messages[i-1]);
            ((Label) v.getChildren().get(i)).setFont(new Font(20));
        }
    }
}
*/
class randomNameField implements EventHandler<ActionEvent>{
    private TextField text;
    private randomName name;

    public randomNameField(TextField text){
        this.text = text;
        this.name = new randomName();
    }

    @Override
    public void handle(ActionEvent arg0){
        text.setText(name.randomTString());
    }
}
class SendMessageHandler implements EventHandler<ActionEvent>{
    private VBox v;
    private TextField text;
    private ScrollPane scroll;
    private int i;

    public SendMessageHandler (VBox v, TextField text, ScrollPane scroll) {
        this.v=v;
        this.text=text;
        this.scroll=scroll;
        this.i = 0;
    }

    private Label makeLabel(String text){
        Label l = new Label();
        int maxlength = 250;
        if(text.length() > maxlength){
            String copy = text.substring(0, maxlength);
            l.setText(copy);
        }
        else{
            l.setText(text);
        }
        if(i % 2 == 0){
        l.getStyleClass().add("chat-bubble");
        }
        else{
            l.getStyleClass().add("chat-bubble2");
        }
        i++;
        l.setMaxSize(200, Double.MAX_VALUE);
        l.setMinHeight(500);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    public void handle (ActionEvent arg0) {
        Label l = makeLabel(text.getText());
        v.getChildren().add(l);
        v.heightProperty().addListener(observable -> scroll.setVvalue(1D));
    }
}
class randomTextField implements EventHandler<ActionEvent>{
    private TextField text;
    private randomText r;

    public randomTextField(TextField text){
        this.text = text;
        this.r = new randomText();
    }

    @Override
    public void handle(ActionEvent arg0) {
        // TODO Auto-generated method stub
        text.setText(r.randomTString());
    }
}