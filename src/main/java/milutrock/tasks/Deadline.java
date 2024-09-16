package milutrock.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import milutrock.exceptions.InvalidTaskFormatException;

/**
 * A task with a specific deadline in the form of a date and time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Parse a string input to create a Deadline object with a
     * specified datetime.
     * 
     * @param input String input from the user.
     * @return A Deadline object created from the given input. 
     */
    public static Deadline getDeadlineFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 9) {
            throw new InvalidTaskFormatException("Deadline");
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2) {
            throw new InvalidTaskFormatException("Deadline");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime datetime = LocalDateTime.parse(parts[1], formatter);

        return new Deadline(parts[0], datetime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
