package tasks;

import utils.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = DateTime.formatDate(startTime);
        this.endTime = DateTime.formatDate(endTime);
    }

    @Override
    public String toFileString() {
        return String.format("E %s | %s-%s", super.toFileString(),
                this.startTime.format(DateTime.DateTimeFileOutputFormatter),
                this.endTime.format(DateTime.DateTimeFileOutputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.startTime.format(DateTime.DateTimeFileOutputFormatter),
                this.endTime.format(DateTime.DateTimeFileOutputFormatter));
    }
}
