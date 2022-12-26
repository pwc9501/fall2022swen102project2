package Controller;

import Random.RandomTxtSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * This event handler helps with picking a random name to be displayed on the user interface.
 * 
 *  @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */
public class RandomNameField implements EventHandler<ActionEvent>{
    private TextField text;
    private RandomTxtSelector name;
    private final String FILE_NAME = "preset_name.txt";

    public RandomNameField(TextField text){
        this.text = text;
        this.name = new RandomTxtSelector(FILE_NAME);
    }

    @Override
    public void handle(ActionEvent arg0){
        text.setText(name.randomTString());
    }
}
