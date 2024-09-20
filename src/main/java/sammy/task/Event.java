package sammy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;



    public Event(String description, String startTime, String endTime) {
        super(description);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert startTime != null && !startTime.isEmpty() : "Start time cannot be null or empty";
        assert endTime != null && !endTime.isEmpty() : "End time cannot be null or empty";

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);

        assert this.endTime.isAfter(this.startTime) : "End time must be after start time";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) +
                " to: " +
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}

