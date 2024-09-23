package taskpack;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Used to represent an Event task.
 */
public class Event extends Task {

    private String start;
    private String end;

    /**
     * Constructor method
     * @param name Name of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     * @param isMarked Task completion status.
     */
    public Event(String name, String start, String end, boolean isMarked) {
        super(name, isMarked);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public LocalDateTime getDueDate() {
        return null;
    }

    @Override
    public String getStart() {
        return this.start;
    }

    @Override
    public String getEnd() {
        return this.end;
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method.
     * @return The String to be saved into the write file
     */
    @Override
    public String toParseableString() {
        String s = "e,";
        if (this.isMarked()) {
            s += "m,";
        } else {
            s += "u,";
        }
        s += this.getName() + " /from " + this.start + " /to " + this.end;
        return s;
    }
}
