package Controller;

import java.sql.Time;

import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ScheduleText implements EventHandler<ActionEvent> {
    private int H;
    private int M;
    private String str;
    private HBox hbox;

    public ScheduleText(HBox hbox)
    {
        this.hbox = hbox;
        this.H = Integer.parseInt(hbox.getChildren().get(0).toString());
        this.M = Integer.parseInt(hbox.getChildren().get(2).toString());
        this.str = hbox.getChildren().get(3).toString();
    }

    @Override
    public void handle(ActionEvent arg0) {

        if (str.equals("PM"))
        {
            H += 12;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(java.time.LocalDate.now());
        sb.append(" ");
        sb.append(H);
        sb.append(":");
        sb.append(M);
        sb.append(":");
        sb.append("00");
        String s = sb.toString();
        Schedule sche = new Schedule(s);
        sche.run();


    }
    
}
