package Controller;

import Random.randomName;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class randomNameField implements EventHandler<ActionEvent>{
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
