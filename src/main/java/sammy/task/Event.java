package sammy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;



    public Event(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) +
                " to: " +
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}

