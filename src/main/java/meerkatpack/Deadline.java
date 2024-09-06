package meerkatpack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime duedate;
    public Deadline(String name, LocalDateTime duedate) {
        super(name);
        this.duedate = duedate;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return "[D]" + super.toString() + " (by: " + this.duedate.format(formatter) + ")";
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
        if (this.isCompleted()) {
            s += "m,";
        }
        else {
            s += "u,";
        }
        s += this.getName() + "," + this.duedate;
        return s;
    }
}
