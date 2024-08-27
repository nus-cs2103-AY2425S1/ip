import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String start;
    private String formattedStartTime;
    private String formattedEndTime;

    protected String end;
    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        LocalDate startTime = LocalDate.parse(start);
        formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.end = end;
        LocalDate endTime = LocalDate.parse(end);
        formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }

    @Override
    public String toSaveFormat() {
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // store format: E | 0 | book event | 2pm | 5pm
        return "E | " + storeCompleted + " | " + this.description + " | " + formattedStartTime + " | " + formattedEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }
}
