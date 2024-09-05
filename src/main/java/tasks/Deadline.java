package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.GrokInvalidUserInputException;
import exceptions.InvalidIsoFormatException;

/**
 * A Deadline stores an additional deadline field in ISO date format.
 */
public class Deadline extends Task {
    private LocalDate due;

    /**
     * Initializes a deadline with the following inputs, assuming a deadline that is not yet done:
     * @param description title, or name, of deadline
     * @param due deadline of date which MUST be in ISO yyyy-mm-dd format
     * @throws GrokInvalidUserInputException if user input is invalid - empty description, or non-ISO dates
     */
    public Deadline(String description, String due) throws GrokInvalidUserInputException {
        super(description);
        try {
            this.due = LocalDate.parse(due);
        } catch (DateTimeParseException e) {
            throw new InvalidIsoFormatException();
        }
    }

    /**
     * Initializes a deadline with the following inputs:
     * @param description title, or name, of deadline
     * @param due deadline of date which MUST be in ISO yyyy-mm-dd format
     * @param isDone whether the deadline has been met or not
     * @throws GrokInvalidUserInputException if user input is invalid - empty description, or non-ISO dates
     */
    public Deadline(String description, String due, Boolean isDone) throws GrokInvalidUserInputException {
        super(description, isDone);
        try {
            this.due = LocalDate.parse(due);
        } catch (DateTimeParseException e) {
            throw new InvalidIsoFormatException();
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "D", super.serialize(), due.toString());
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + "(by: " + due.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
