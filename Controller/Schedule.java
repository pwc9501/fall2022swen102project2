package Controller;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class Schedule extends TimerTask{
    private String str;
    private TextField input;

    public Schedule(String str)
    {
        this.str = str;
    }
    
    /**
     * Executes code at specified time
     */
    @Override
    public void run() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            java.util.Date date = dateFormatter.parse(str);
            Timer timer = new Timer();
            timer.schedule(new Schedule(str), date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println("This is a test for scheduling");
    }

}