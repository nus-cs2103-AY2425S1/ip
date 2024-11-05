package jag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline Class that extends Task to store the required information
 * of a Deadline Task and return its String representation when called upon
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public void update(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * A custom construtor for the Deadline Task that takes in a description
     * and a date and time parameter for its due date.
     *
     * @param description is a String representation of the description sent
     *                    by the user
     * @param by is an instance of LocalDateTime to be set as the due date of the
     *           task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh mm ss")) + ")";
    }

}
