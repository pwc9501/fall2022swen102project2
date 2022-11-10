package Controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import View.GUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class popUpTextHandler implements EventHandler<ActionEvent>{
    private TextField time;
    private TextField sender;
    private TextField text;
    private VBox v;
    private Button b;
    private Stage popUpStage;

    public popUpTextHandler(TextField time, TextField sender, TextField text, VBox v, Button b, Stage popUpStage)
    {
        this.time = time;
        this.sender = sender;
        this.text = text;
        this.v = v;
        this.b = b;
        this.popUpStage = popUpStage;
    }

    private Label makeLabel(String text){
        Label l = new Label(text);
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        l.setMinHeight(40);
        l.setAlignment(Pos.CENTER);
        l.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(5), Insets.EMPTY)));
        return l;
    }

    
    /**
     * Executes code at specified time
     */

    public void text(){
        Label l = makeLabel(text.getText());
        if(!sender.getText().isEmpty()){
            l.setText("From: " + sender.getText() + " = " + text.getText());
        }
        v.getChildren().add(l);
        
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
            System.out.println(duration);
            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(duration), ev -> {
                text();
            }));
            timeline.play();
        }
        else{
            text();
        }
        String messageRecievedAudio = "Audio/MessageRecieved.mp3";    
        Media sound = new Media(new File(messageRecievedAudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play(); 
        sender.setEditable(false);
        b.setDisable(true);
        popUpStage.close();
     }
}