package donk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dt;

    /**
     * Constructs a donk.task.Deadline object with the specified description and deadline date-time.
     *
     * @param description The description of the task.
     * @param dt The deadline date-time in the specified format.
     */
    public Deadline(String description, String dt) {
        super(description, "D");
        try {
            this.dt = LocalDateTime.parse(dt);
        } catch(Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            this.dt = LocalDateTime.parse(dt, formatter);
        }

    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (due: " + this.dt + ")";
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.dt.toString();
    }

}


