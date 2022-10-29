package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    
    /**
     * Executes code at specified time
     */

    public void text(){
        String[] messages = new String[v.getChildren().size()];
        for(int i = 0; i < v.getChildren().size(); i++){
            messages[i] = ((Label) v.getChildren().get(i)).getText();
            ((Label) v.getChildren().get(i)).setText("");
        }
        if(!sender.getText().isEmpty()){
            ((Label) v.getChildren().get(0)).setText("From: " + sender.getText() + " = " + text.getText());
        }
        else{
            ((Label) v.getChildren().get(0)).setText(text.getText());
        }
        ((Label) v.getChildren().get(0)).setFont(new Font(20));
        for(int i = 1; i < v.getChildren().size(); i++){
            ((Label) v.getChildren().get(i)).setText(messages[i-1]);
            ((Label) v.getChildren().get(i)).setFont(new Font(20));
        }
    }

     @Override
     public void handle(ActionEvent arg0) {
         // TODO Auto-generated method stub
         if(!time.getText().isEmpty()){
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = dateFormatter.parse(time.getText());
                Timer timer = new Timer();
                TimerTask task = new TimerTask(){
                    @Override
                    public void run() {
                        text();
                    }
                };
                timer.schedule(task, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            text();
        }
     }
}