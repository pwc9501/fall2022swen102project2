package Controller;

import javax.crypto.spec.RC2ParameterSpec;

import Random.randomText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class UpdateMessageField implements EventHandler<ActionEvent> {
    private TextArea txtMessage;

    public UpdateMessageField(TextArea txtMessage) {
        this.txtMessage = txtMessage;

    }

    @Override
    public void handle(ActionEvent arg0) {
        randomText r = new randomText();
        txtMessage.setText(r.randomTString());
        
    }
    
}
