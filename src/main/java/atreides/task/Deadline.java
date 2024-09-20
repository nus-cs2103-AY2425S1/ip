package atreides.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import atreides.ui.AtreidesException;


/**
 * Represents a task with a deadline in the task list.
 * Extends the base {@code Task} class by adding a deadline attribute.
 */
public class Deadline extends Task {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected String by;
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with a description and a specified deadline.
     *
     * @param description The description of the task.
     * @param by The deadline in the form of either "YYYY-MM-DD" or a string matching the pattern "MMM dd yyyy HH:mm".
     * @throws AtreidesException If the deadline format is invalid.
     */
    public Deadline(String description, String by) throws AtreidesException {
        super(description);
        this.by = by;
        assert !by.isEmpty();
        if (by.contains("-")) {
            String[] parseDT = by.split("-");
            System.out.println(Arrays.toString(parseDT));
            this.deadline = LocalDateTime.of(Integer.parseInt(parseDT[0]),
                    Integer.parseInt(parseDT[1]), Integer.parseInt(parseDT[2]), 23, 59);
        } else if (validateDate(by)) {
            this.deadline = LocalDateTime.parse(by, FORMAT);
        } else {
            throw new AtreidesException("Invalid deadline format");
        }
    }

    /**
     * Validates whether the given date string matches the predefined date format.
     *
     * @param date The date string to be validated.
     * @return true if the date string matches the format, false otherwise.
     */
    public boolean validateDate(String date) {
        try {
            LocalDateTime.parse(date, FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof Deadline other) {
            return other.deadline.equals(this.deadline) && other.by.equals(this.by);
        }
        return false;
    }

    @Override
    public String write() {
        return "D" + super.write() + " | " + deadline.format(FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMAT) + ")";
    }
}
