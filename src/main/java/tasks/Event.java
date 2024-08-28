package tasks;

import utils.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileString() {
        return String.format("E %s | %s to %s", super.toFileString(),
                this.startTime.format(DateTime.DateTimeFileOutputFormatter),
                this.endTime.format(DateTime.DateTimeFileOutputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.startTime.format(DateTime.DateTimePrintOutputFormatter),
                this.endTime.format(DateTime.DateTimePrintOutputFormatter));
    }
}
