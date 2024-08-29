import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate startTime;
    LocalDate endTime;

    public Event(String task, LocalDate startTime, LocalDate endTime, boolean isMarked) {
        super(task, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTaskOnList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);

        if (isMarked) {
            return "[E][X] " + this.task + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        } else {
            return "[E][ ] " + this.task + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + (this.isMarked ? "1" : "0") + " | " + this.task + " | " + this.startTime + " | " + this.endTime;
    }

}
