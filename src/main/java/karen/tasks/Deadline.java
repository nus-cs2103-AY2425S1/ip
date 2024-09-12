package karen.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which contains the name and dueDate
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor to initialize the name and dueDate
     * @param name a String representing the Deadline's name
     * @param dueDate a String representing the dueDate
     */
    public Deadline(String name, String dueDate) {
        super(name);
        //param dueDate in the form "yyyy-mm-dd HHmm"
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
        return "[D]" + s + String.format(" (by: %s)", this.dueDate.format(formatter));
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("D | %d | %s | %s", markedInt, this.getName(), this.dueDate.format(formatter));
    }
}
