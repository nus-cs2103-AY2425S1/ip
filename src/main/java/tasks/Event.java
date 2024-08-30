package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String name, LocalDate startTime, LocalDate endTime, boolean done) {
        super(name, done);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    @Override
    public String toData() {
        return "E" + super.toData() + "%" + this.startTime + "|" + this.endTime;
    }
}
