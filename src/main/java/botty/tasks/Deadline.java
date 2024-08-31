package botty.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

/**
 * A task with an end date
 */
public class Deadline extends Task {
    // The end date of the task
    private final LocalDate endDate;

    /**
     * Constructs a {@code Deadline} with the given inputs
     * @param completed whether the task is completed
     * @param description the description
     * @param endDate the end date
     * @throws EmptyArgumentException if the description or end date is empty
     * @throws IncorrectDateFormatException if the end date is formatted incorrectly
     */
    public Deadline(boolean completed, String description, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (endDate.isEmpty()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }

    /**
     * Constructs a {@code Deadline} with the given inputs, set to not completed
     * @param description the description
     * @param endDate the end date
     * @throws EmptyArgumentException if the description or end date is empty
     * @throws IncorrectDateFormatException if the end date is formatted incorrectly
     */
    public Deadline(String description, String endDate) throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, description, endDate);
    }

    /**
     * Returns a string representation of the {@code Deadline}
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Constructs a {@code Deadline} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed {@code Deadline}
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Deadline fromDataString(String taskString) throws BottyException {
        if (!taskString.matches("D \\| [10] \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String endDate = arguments[3];

        return new Deadline(completed, description, endDate);
    }

    /**
     * Returns a string representation for storage in local storage
     */
    @Override
    public String toDataString() {
        return "D | " + getCompletedAndDescription() + " | " + endDate;
    }
}
