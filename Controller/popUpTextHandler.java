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
/**
 * This event Handler helps with displaying the text and name on the user interface based on user input.
 * This event Handler also helps with displaying the text at certain time frame.
 * 
 * @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */
public class PopUpTextHandler implements EventHandler<ActionEvent>{
    private TextField time;
    private TextField sender;
    private TextField text;
    private VBox vMessageList;
    private VBox beginningBox;
    private Button btnRandomSender;
    private Button popUp;
    private Stage popUpStage;
    private ComboBox<String> TextBubbleBox;

    public PopUpTextHandler(TextField time, TextField sender, TextField text, VBox vMessageList, Button btnRandomSender, Stage popUpStage, ComboBox<String> TextBubbleBox, VBox beginningBox, Button popUp)
    {
        this.time = time;
        this.sender = sender;
        this.text = text;
        this.vMessageList = vMessageList;
        this.btnRandomSender = btnRandomSender;
        this.popUp = popUp;
        this.popUpStage = popUpStage;
        this.TextBubbleBox = TextBubbleBox;
        this.beginningBox = beginningBox;
    }

    private Label makeLabel(String text){
        Label lblMessage = new Label(text);
        int maxlength = 200;
        if(text.length() > maxlength){
            lblMessage.setMinHeight(text.length() * .6);
        }
        else{
            lblMessage.setMinHeight(50);
        }
        if (TextBubbleBox.getValue() == null){
            lblMessage.getStyleClass().add("chat-bubble");
        }
        else if(TextBubbleBox.getValue().equals("Reciever Message")){
            lblMessage.getStyleClass().add("chat-bubble");
        }
        else{
            lblMessage.getStyleClass().add("chat-bubble2");
        }
        lblMessage.setMaxSize(200, Double.MAX_VALUE);
        lblMessage.setAlignment(Pos.CENTER);
        return lblMessage;
    }

    
    /**
     * Executes code at specified time
     */
    public void text(){
        String messageAudio;    
        if (TextBubbleBox.getValue().equals("Reciever Message")) {
            messageAudio = "Audio/MessageRecieved.mp3";    
        }
        else {
            messageAudio = "Audio/MessageSent.mp3";
        }
        Media sound = new Media(new File(messageAudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        if(!text.getText().isEmpty()){
            Label lblMessage = makeLabel(text.getText());
            vMessageList.getChildren().add(lblMessage);
            mediaPlayer.play();
            text.setText("");
            TextBubbleBox.getSelectionModel().selectFirst();
        }
        ((Label) beginningBox.getChildren().get(1)).setText(sender.getText());
    }

     @Override
     public void handle(ActionEvent arg0) {
         if(!time.getText().isEmpty()){
            popUp.setDisable(true);
            LocalTime localTime = LocalTime.now();
            String[] dataAndTimeString = time.getText().split(" ");
            String[] timeString = dataAndTimeString[1].split(":");
            Double duration = (Integer.parseInt(timeString[0]) - localTime.getHour()) * 60 + (Integer.parseInt(timeString[1]) - localTime.getMinute() 
            + (Double.parseDouble(timeString[2]) - localTime.getSecond()) / 60);
            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(duration), ev -> {
                text();
                popUp.setDisable(false);
            }));
            timeline.play();
        }
        else{
            text();
        }
        if(!sender.getText().isEmpty()){
            sender.setEditable(false);
            btnRandomSender.setDisable(true);
        }
        time.setText("");
        popUpStage.close();
     }
}