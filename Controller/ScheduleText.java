package Controller;

import java.sql.Time;

import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class ScheduleText implements EventHandler<ActionEvent> {
    private int H;
    private int M;
    private String str;

    public ScheduleText(int H, int M, String str)
    {
        this.H = H;
        this.M = M;
        this.str = str;
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
