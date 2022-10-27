package Controller;

import javax.crypto.spec.RC2ParameterSpec;

import Random.randomText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RandomMessageField implements EventHandler<ActionEvent> {
    private TextField txtMessage;

    public RandomMessageField(TextField txtMessage) {
        this.txtMessage = txtMessage;

    }

    @Override
    public void handle(ActionEvent arg0) {
        randomText r = new randomText();
        txtMessage.setText(r.randomTString());
        
    }
    
}
