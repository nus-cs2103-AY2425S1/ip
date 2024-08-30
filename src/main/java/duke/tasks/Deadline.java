package duke.tasks;

import duke.exceptions.InvalidDateException;
import duke.parsers.Time;

/**
 * tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    private Time by;
    public Deadline(String name, String by) throws InvalidDateException {
        super(name);
        this.by = new Time(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }

    @Override
    public String toDataFormat() {
        return "D" + super.toDataFormat() + "|" + by.toString();
    }
}
