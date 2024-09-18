package donk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a task with deadline, child class of Task
 */
public class Deadline extends Task {
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
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            this.dt = LocalDateTime.parse(dt, formatter);
        }

    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string in the format "[D] description (due: dt)", where "description"
     *         is the task description and "dt" is the due date and time.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (due: " + this.dt + ")";
    }

    /**
     * Returns a string representation of the Deadline object suitable for saving to a file.
     *
     * @return A string in the format "taskType|isDone|description|dt", where "taskType"
     *         is the type of task, "isDone" is 1 if the task is completed, otherwise 0,
     *         "description" is the task description, and "dt" is the due date and time.
     */

    public String getIsoDate() {
        return this.dt.toString();
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.dt.toString();
    }

    @Override
    public int compareTo(Task task) {
        if (task.getTaskType() == "T") {
            return 1;
        } else {
            return this.getIsoDate().compareTo(task.getIsoDate());
        }
    }

}


