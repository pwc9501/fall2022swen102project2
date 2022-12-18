package Controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This event Handler helps convert the time from standard to military time. This is used to
 * help figure out what time exactly the user wants to schedule a text.
 * 
 * @author Liang Chu, Noah Landis, Kevin Sakowicz, Yanzhen Luo, Patrick Collins
 */

public class ScheduleText implements EventHandler<ActionEvent> {
    private ComboBox<Integer> cboxHour;
    private ComboBox<Integer> cboxMinute;
    private ComboBox<String> str;
    private TextField textField;
    private Stage scheduleStage;
    public ScheduleText(HBox hbox, TextField textField, Stage popUpStage, Stage scheduleStage)
    {
        this.textField = textField;
        this.scheduleStage = scheduleStage;
        this.cboxHour = (ComboBox<Integer>) hbox.getChildren().get(0);
        this.cboxMinute = (ComboBox<Integer>) hbox.getChildren().get(2);
        this.str = (ComboBox<String>) hbox.getChildren().get(3);
    }

    @Override
    public void handle(ActionEvent arg0) {
        int hour = cboxHour.getValue();
        int minute = Integer.valueOf(String.valueOf(cboxMinute.getValue()));
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
        cboxHour.setValue(null);
        cboxMinute.setValue(null);
        str.setValue(null);
        scheduleStage.close();
    }
}
