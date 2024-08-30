/**
 * This class represents tasks that start at a specific date/time
 * and ends at a specific date/time
 */
package duke.tasks;

import duke.exceptions.InvalidDateException;
import duke.parsers.Time;

public class Event extends Task {
    private Time from;
    private Time to;

    public Event(String name, String from, String to) throws InvalidDateException {
        super(name);
        this.from = new Time(from);
        this.to = new Time(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.toString() + " to: "
                + to.toString() + ")";
    }

    @Override
    public String toDataFormat() {
        return "E" + super.toDataFormat() + "|" + from.toString() + "|"
                + to.toString();
    }
}
