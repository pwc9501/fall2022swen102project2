package Controller;

import View.GUIDepreciated;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class UpdateSenderField implements EventHandler<ActionEvent> {
    private TextField txtSender;

    public UpdateSenderField(TextField txtSender) {
        this.txtSender = txtSender;
    }

    @Override
    public void handle(ActionEvent arg0) {
        txtSender.setText("Random sender");
    }
}
