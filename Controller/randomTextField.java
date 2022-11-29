package Controller;

import Random.randomText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class randomTextField implements EventHandler<ActionEvent>{
    private TextField text;
    private randomText r;

    public randomTextField(TextField text){
        this.text = text;
        this.r = new randomText();
    }

    @Override
    public void handle(ActionEvent arg0) {
        text.setText(r.randomTString());
    }
}