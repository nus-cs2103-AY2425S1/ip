package taskpack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Used to represent a Deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Constructor for a deadline task.
     * @param name Name of the deadline task.
     * @param dueDate Deadline of the deadline task.
     */
    public Deadline(String name, LocalDateTime dueDate, boolean isMarked) {
        super(name, isMarked);
        this.dueDate = dueDate;
    }

    @Override
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Override
    public String getStart() {
        return null;
    }

    @Override
    public String getEnd() {
        return null;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(formatter) + ")";
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method.
     * @return The String to be saved into the write file
     */
    @Override
    public String toParseableString() {
        String s = "d,";
        String due = this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        if (this.isMarked()) {
            s += "m,";
        } else {
            s += "u,";
        }
        s += this.getName() + " /by " + due;
        return s;
    }
}
