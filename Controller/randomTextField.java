package Controller;

import Random.RandomTxtSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * This event handler helps with picking a random string of text to be displayed on the user interface.
 * 
 *  @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */
public class randomTextField implements EventHandler<ActionEvent>{
    private TextField text;
    private RandomTxtSelector randomMessage;
    private final String FILE_NAME = "preset_text.txt";

    public randomTextField(TextField text){
        this.text = text;
        this.randomMessage = new RandomTxtSelector(FILE_NAME);
    }

    @Override
    public void handle(ActionEvent arg0) {
        text.setText(randomMessage.randomTString());
    }
}