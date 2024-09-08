package cloud.task;

import cloud.exception.CloudException;
import cloud.util.DateTime;

public class Deadline extends Task {
    protected DateTime by;

    /**
     * Constructs a Deadline object
     *
     * @param desc description of the task
     * @param by   expiry date and time of the task, in the format "dd/MM/yyyy HH:mm"
     * @throws CloudException if date or time input format is invalid
     */
    public Deadline(String desc, DateTime by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String formatSave() {
        return "D | "
                + super.formatSave()
                + " | " + this.by.formatSave();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
