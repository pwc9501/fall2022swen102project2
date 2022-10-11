package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

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
