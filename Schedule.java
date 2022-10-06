import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Schedule extends TimerTask {
    public static void main(String[] args) throws ParseException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = dateFormatter.parse("2022-10-06 11:34:00");
        Timer timer = new Timer();
        timer.schedule(new Schedule(), date);
    }
    
    /**
     * Executes code at specified time
     */
    @Override
    public void run() {
        System.out.println("This is a test for scheduling");
    }
}