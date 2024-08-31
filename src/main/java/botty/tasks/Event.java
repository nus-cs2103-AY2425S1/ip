package botty.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

/**
 * A task with a start date and an end date
 */
public class Event extends Task {
    // The start date of the task
    private final LocalDate startDate;
    // The end date of the task
    private final LocalDate endDate;

    /**
     * Constructs an {@code Event} with the given arguments
     * @param completed if the {@code Event} is completed
     * @param description the description of the {@code Event}
     * @param startDate the start date of the {@code Event}
     * @param endDate the end date of the {@code Event}
     * @throws EmptyArgumentException if description, start date or end date is empty
     * @throws IncorrectDateFormatException if start date or end date is in the incorrect date format
     */
    public Event(boolean completed, String description, String startDate, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (startDate.isEmpty()) {
            throw new EmptyArgumentException("start date");
        }
        if (endDate.isEmpty()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("start date needs to be in format yyyy-mm-dd");
        }
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }
    /**
     * Constructs an {@code Event} with the given arguments, set to not completed
     * @param description the description of the {@code Event}
     * @param startDate the start date of the {@code Event}
     * @param endDate the end date of the {@code Event}
     * @throws EmptyArgumentException if description, start date or end date is empty
     * @throws IncorrectDateFormatException if start date or end date is in the incorrect date format
     */
    public Event(String description, String startDate, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, description, startDate, endDate);
    }

    /**
     * Returns a string representation of the event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Constructs an {@code Event} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed event
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Event fromDataString(String taskString) throws BottyException {
        if (!taskString.matches("E \\| [10] \\| (.*?) \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String startDate = arguments[3];
        String endDate = arguments[4];

        return new Event(completed, description, startDate, endDate);
    }

    /**
     * Returns a string representation of the {@code Event} that is used for local storage
     */
    @Override
    public String toDataString() {
        return "E | " + getCompletedAndDescription() + " | " + startDate + " | " + endDate;
    }
}
