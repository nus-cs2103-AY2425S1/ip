package Majima.task;

import Majima.MajimaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Parses the input from the user's console. If valid, adds a task of type Deadline
     * to the list and .txt file, by calling other methods, such as those in Majima.Parse.
     *
     * @param description String Description of the deadline
     * @param by String deadline in the format of "dd-MM-YYYY HHmm"
     */
    public Deadline(String description, String by) throws MajimaException {
        super(description, TaskType.DEADLINE);
        try {
            this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new MajimaException("Kiryu-chan! Follow the standard format of 'dd-mm-yyyy HHmm'!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return "[D] | " + getStatusIcon() + " | " + getDescription() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
    }
}
