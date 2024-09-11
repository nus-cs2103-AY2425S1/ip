package buddybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        assert by != null : "Deadline should not be empty!";
        this.by = by;
    }

    /**
     * Return a String of the Deadline in the format the file reader can recognise
     * @return String
     */
    public String toFile() { //prototype in case of future modification
        return "D" + super.toFile() + "|" + by;
    }

    /**
     * Return a String of the Deadline with its status and description and date
     * @return String
     */
    @Override
    public String toString() { //prototype in case of future modification
        return "[D]" + super.toString() + " by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
