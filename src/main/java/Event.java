import java.time.LocalDateTime;

class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String taskDescription, String startTime, String endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.startTime = toLocalDateTime(startTime);
        this.endTime = toLocalDateTime(endTime);
    }

    public Event(String taskDescription, String startTime, String endTime) {
        this(taskDescription, startTime, endTime, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatYearMonthDay(this.startTime) +
                " " + "to: " + formatYearMonthDay(this.endTime) + ")";
    }
}
