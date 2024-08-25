import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    Event(String name, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (from: " + this.startTime.format(DateTimeFormatter
                .ofPattern("MMM d yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    String toSaveState() {
        return "E" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/" + this.startTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd"))
                + "/" + this.endTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd")) + "\n";
    }
}
