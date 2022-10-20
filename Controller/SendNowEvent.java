package Controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SendNowEvent implements EventHandler<ActionEvent> {
    private Stage popUp;
    private BorderPane root;
    private TextArea message;
    private TextField sender;

    public SendNowEvent(Stage popUp, BorderPane root, TextArea message, TextField sender) {
        this.popUp = popUp;
        this.root = root;
        this.message = message;
        this.sender = sender;
    }
    @Override
    public void handle(ActionEvent arg0) {
        popUp.close();
        VBox msgDisplay = new VBox();
        Label contact = new Label(sender.getText());
        Label messages = new Label();
        messages.setText(messages.getText() + message.getText());
        contact.setPadding(new Insets(0, 0, 0, root.getWidth()/2));
        msgDisplay.getChildren().addAll(
            contact,
            messages
        );
        String messageRecievedAudio = "Audio/MessageRecieved.mp3";    
        Media sound = new Media(new File(messageRecievedAudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play(); 
;


        
        
    }
    
}
