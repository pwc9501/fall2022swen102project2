package Controller;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScheduleText implements EventHandler<ActionEvent> {
    private ComboBox<Integer> H;
    private ComboBox<Integer> M;
    private ComboBox<String> str;
    private HBox hbox;
    private TextField textField;
    private Stage popStage;
    private Stage scheduleStage;
    public ScheduleText(HBox hbox, TextField textField, Stage popUpStage, Stage scheduleStage)
    {
        this.hbox = hbox;
        this.textField = textField;
        this.popStage = popUpStage;
        this.scheduleStage = scheduleStage;
        
        this.H = (ComboBox<Integer>) hbox.getChildren().get(0);
        this.M = (ComboBox<Integer>) hbox.getChildren().get(2);
        this.str = (ComboBox<String>) hbox.getChildren().get(3);
    }

    @Override
    public void handle(ActionEvent arg0) {
        int hour = H.getValue();
        int minute = Integer.parseInt(String.valueOf(M.getValue()));
        String timezone = str.getValue();
        if (timezone.equals("PM") && (hour >= 1 && hour <= 11))
        {
            hour += 12;
        }
        else if(timezone.equals("AM") && hour == 12){
            hour = 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(java.time.LocalDate.now());
        sb.append(" ");
        if(hour < 10 && timezone.equals("AM")){
            sb.append("0" + hour);
        }
        else{
            sb.append(hour);
        }
        sb.append(":");
        if(minute < 10){
            sb.append("0" + minute);
        }
        else{
            sb.append(minute);
        }
        sb.append(":");
        sb.append("00");
        String s = sb.toString();
        textField.setText(s);
        System.out.println(s);
        scheduleStage.close();
       
    }
    
}
