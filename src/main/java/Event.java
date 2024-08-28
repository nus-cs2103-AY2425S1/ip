import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Event extends Task {

    public boolean done;
    public String text;
    public LocalDate start;
    public LocalDate end;

    public Event(String text, LocalDate start, LocalDate end) {
        super(text);
        this.text = text;
        this.start = start;
        this.end = end;
    }
    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[E][" + str + "] " + text + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
