package jeriel.task;
import jeriel.command.*;
import jeriel.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of this deadline in the save format.
     *
     * @return a string in the format "D | 0/1 | description | by"
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }


    /**
     * Returns a string representation of this deadline.
     *
     * @return a string in the format "[D][ ] description (by: MMM d yyyy)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
