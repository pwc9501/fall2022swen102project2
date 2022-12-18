package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
/**
 * This event Handler helps with opening the sub menus in the GUI file. More specifically,
 * this file helps with opening the sub amd scheduler menus.
 * 
 * @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */
public class OpenUserInput implements EventHandler<ActionEvent> {
    private Stage popUp;
    
    public OpenUserInput(Stage popUp) {
        this.popUp = popUp;
    }

    @Override
    public void handle(ActionEvent arg0) {
        popUp.show();
    }
}
