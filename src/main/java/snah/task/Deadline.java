package snah.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to handle Deadline tasks
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for Deadline class
     * @param description Description of the task
     * @param by          Deadline of the task in the format yyyy-mm-dd
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("%s (Deadline: by %s) %s", printStatus(),
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), getDescription());
    }

    /**
     * Returns true if the task contains the keyword, including the 'by' field.
     * @param keyword
     * @return boolean
     */
    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).contains(keyword);
    }

    /**
     * Returns the Deadline in the format to save to file
     * @return Deadline in the format to save to file
     */
    @Override
    public String toSaveFile() {
        return String.format("D:%s:%s:%s", isDone() ? "x" : "", getDescription(), by);
    }

}
