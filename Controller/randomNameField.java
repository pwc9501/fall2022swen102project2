package Controller;

import Random.randomName;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * This event handler helps with picking a random name to be displayed on the user interface.
 * 
 *  @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */
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
