import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String desc, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(desc, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "E" + super.getSaveTaskString() + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + this.startTime + "|to: " + this.endTime + ")";
    }
}
