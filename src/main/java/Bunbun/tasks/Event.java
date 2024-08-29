package Bunbun.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class implements a Event task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Instantiates an Event task that keep tracks of start and end date of task.
     *
     * @param task String description of task.
     * @param start LocalDate object to store start date in yyyy-MM-dd format.
     * @param end LocalDate object to store end date in yyyy-MM-dd format.
     */
    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String genFileString() {
        String isComplete = (this.isComplete()) ? "true" : "false";
        String taskDescription = String.format("%s;%s;%s;%s;%s\n", "event", isComplete, this.getTask(),
                this.start, this.end);
        return taskDescription;
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString() + String.format("( from: %s to: %s )",
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return str;
    }
}
