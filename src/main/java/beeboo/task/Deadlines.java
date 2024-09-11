package beeboo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import beeboo.components.TimeConverter;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;


/**
 * The Deadlines class represents a task with a specific deadline. It extends the
 * Tasks class and includes functionality for managing deadlines.
 */
public class Deadlines extends Tasks {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a");
    private LocalDateTime date;

    /**
     * Constructs a Deadlines instance with the specified description and deadline date.
     *
     * @param description the description of the deadline
     * @param date        the LocalDateTime representing the deadline
     */
    public Deadlines(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the type icon for deadlines.
     *
     * @return the type icon as a code String
     */
    @Override
    protected String typeIcon() {
        return "[D]";
    }

    /**
     * Creates a Deadlines instance from the given text input. The input should include
     * a description and a deadline date in the format "by date". The method parses the input,
     * validates the description and date, and returns a new Deadlines object.
     *
     * @param text the input text containing description and deadline date
     * @return a code Deadlines instance
     * @throws NoDescriptionException if the description is missing or empty
     * @throws InvalidDateException    if the date is invalid or improperly formatted
     */
    public static Deadlines createDeadline(String text) throws NoDescriptionException, InvalidDateException {
        int descriptionEnd = text.indexOf('/');
        if (descriptionEnd == -1) {
            throw new InvalidDateException(text);
        }
        String description = text.substring(0, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateCommand = text.substring(descriptionEnd + 1).trim();
        if (!dateCommand.startsWith("by")) {
            throw new InvalidDateException(text);
        }
        String date = dateCommand.substring(2).trim();
        LocalDateTime dateTime;
        try {
            dateTime = TimeConverter.timeConverter(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(text);
        }
        return new Deadlines(description, dateTime);
    }

    /**
     * Returns a String representation of the {@code Deadlines} instance, including the
     * description and formatted deadline date.
     *
     * @return a String representation of the deadline
     */
    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(by:" + FORMATTER.format(date).toLowerCase() + ")";
    }

    /**
     * Returns a String representation of the Deadlines instance in a format
     * suitable for saving to storage. The format includes the task type, completion status, description,
     * and deadline date.
     *
     * @return a String representing the saved format of the deadline
     */
    @Override
    public String saveFormat() {
        return "D | " + (super.isDone ? "1 | " : "0 | ") + description + " | " + date;
    }
}
