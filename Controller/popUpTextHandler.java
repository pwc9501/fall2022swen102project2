package Controller;

import java.io.File;
import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class popUpTextHandler implements EventHandler<ActionEvent>{
    private TextField time;
    private TextField sender;
    private TextField text;
    private VBox v;
    private VBox beginningBox;
    private Button b;
    private Stage popUpStage;
    private ComboBox<String> TextBubbleBox;

    public popUpTextHandler(TextField time, TextField sender, TextField text, VBox v, Button b, Stage popUpStage, ComboBox<String> TextBubbleBox, VBox beginningBox)
    {
        this.time = time;
        this.sender = sender;
        this.text = text;
        this.v = v;
        this.b = b;
        this.popUpStage = popUpStage;
        this.TextBubbleBox = TextBubbleBox;
        this.beginningBox = beginningBox;
    }

    private Label makeLabel(String text){
        Label l = new Label(text);
        int maxlength = 200;
        if(text.length() > maxlength){
            l.setMinHeight(text.length() * .6);
        }
        else{
            l.setMinHeight(50);
        }
        if (TextBubbleBox.getValue() == null){
            l.getStyleClass().add("chat-bubble");
        }
        else if(TextBubbleBox.getValue().equals("Reciever Message")){
            l.getStyleClass().add("chat-bubble");
        }
        else{
            l.getStyleClass().add("chat-bubble2");
        }
        l.setMaxSize(200, Double.MAX_VALUE);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    
    /**
     * Executes code at specified time
     */

    public void text(){
        String messageRecievedAudio = "Audio/MessageRecieved.mp3";    
        Media sound = new Media(new File(messageRecievedAudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        if(!text.getText().isEmpty()){
            Label l = makeLabel(text.getText());
            v.getChildren().add(l);
            mediaPlayer.play();
        }
    }

     @Override
     public void handle(ActionEvent arg0) {
         // TODO Auto-generated method stub
         if(!time.getText().isEmpty()){
            LocalTime localTime = LocalTime.now();
            String[] dataAndTimeString = time.getText().split(" ");
            String[] timeString = dataAndTimeString[1].split(":");
            Double duration = (Integer.parseInt(timeString[0]) - localTime.getHour()) * 60 + (Integer.parseInt(timeString[1]) - localTime.getMinute() 
            + (Double.parseDouble(timeString[2]) - localTime.getSecond()) / 60);
            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(duration), ev -> {
                text();
            }));
            timeline.play();
        }
        else{
            text();
        }
      
        if(!sender.getText().isEmpty()){
            ((Label) beginningBox.getChildren().get(1)).setText(sender.getText());
            sender.setEditable(false);
            b.setDisable(true);
        }
        popUpStage.close();
     }
}