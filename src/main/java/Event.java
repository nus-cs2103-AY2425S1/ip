import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected String start;
    protected String end;
    private String formattedStartTime;
    private String formattedEndTime;

    private LocalDateTime parsedStart;
    private LocalDateTime parsedEnd;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedStart = LocalDateTime.parse(start, inputFormat);
        this.parsedEnd = LocalDateTime.parse(end, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        this.formattedStartTime = this.parsedStart.format(outputFormat);
        this.formattedEndTime = this.parsedEnd.format(outputFormat);
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
        return "E | " + storeCompleted + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }
}
