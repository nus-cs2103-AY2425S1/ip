import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime start;
    protected LocalDateTime end;
    private String formattedStartTime;
    private String formattedEndTime;

    public Event(String description, String start, String end){
        super(description);

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, inputFormat);
        this.end = LocalDateTime.parse(end, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        this.formattedStartTime = this.start.format(outputFormat);
        this.formattedEndTime = this.end.format(outputFormat);

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
