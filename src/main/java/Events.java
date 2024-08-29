import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")" ;
    }

    @Override
    public String toStore(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E/" + super.getStatus() + "/" + description + "/"
                + start.format(formatter) + "/" + end.format(formatter);
    }
}
