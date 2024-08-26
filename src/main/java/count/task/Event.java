package count.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");
    public Event(String description, String endTime, String startTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime, inputFormat);
        this.endTime = LocalDate.parse(endTime, inputFormat);
    }

    public Event(String description, LocalDate endTime, LocalDate startTime, boolean completion) {
        super(description, completion);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(outputFormat) + " to: " + this.endTime.format(outputFormat) + ")";
    }
}
