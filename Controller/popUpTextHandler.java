package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class popUpTextHandler implements EventHandler<ActionEvent>{
    private TextField time;
    private TextField sender;
    private TextField text;
    private VBox v;

    public popUpTextHandler(TextField time, TextField sender, TextField text, VBox v)
    {
        this.time = time;
        this.sender = sender;
        this.text = text;
        this.v = v;
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
            int duration = (Integer.parseInt(timeString[0]) - localTime.getHour()) * 60 + (Integer.parseInt(timeString[1]) - localTime.getMinute());
            System.out.println(duration);
            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(duration), ev -> {
                text();
            }));
            timeline.play();
        }
        else{
            text();
        }
     }
}