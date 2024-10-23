package screwllum.tasks;

import java.time.LocalDate;

import screwllum.utils.Parser;

/**
 * Represents a task that has to be done before a certain date
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs an object representing a task with a deadline.
     *
     * @param desc  The description (name) of the task.
     * @param by The deadline of the task, which is required to be in a certain format.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = Parser.parseStringToDate(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Parser.parseDateToString(by, "MMM dd yyyy"));
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A String in the format D_status_desc_by.
     */
    public String toSaveFormat() {
        return String.format("D_%s_%s_%s", isDone ? "1" : "0", getDesc(), Parser.parseDateToString(by));
    }
}
