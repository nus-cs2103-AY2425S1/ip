package ekud.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import ekud.exceptions.EkudException;

/**
 * Represents a {@link Task} that has a deadline.
 *
 * @author uniqly
 */
public class DeadlineTask extends Task {
    /** The {@link LocalDateTime} format when parsing input date Strings */
    public static final DateTimeFormatter READ_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);

    /** The {@link LocalDateTime} format when outputting date Strings */
    public static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH);

    /** The deadline of the task */
    protected LocalDateTime deadline;

    /**
     * Constructs a {@link Task} with a deadline.
     *
     * @param description The description of the event.
     * @param deadline A {@link String} representing the deadline.
     * @throws EkudException If description is empty, or if deadline is empty or has invalid format.
     */
    public DeadlineTask(String description, String deadline) throws EkudException {
        super(description);
        if (deadline == null || deadline.isEmpty()) {
            throw new EkudException("""
                    Whoopsies!! Looks like you forgot your deadline!
                    I'll say this once: next time mark your deadline with '/by'.""");
        }
        try {
            this.deadline = LocalDateTime.parse(deadline, READ_FORMAT);
        } catch (DateTimeParseException e) {
            throw new EkudException("""
                    Whoopsies!! It looks like you tried to pass a deadline that I cannot read!
                    I'd recommend that you follow the 'd/M/yyyy HHmm' format. Or else...""");
        }
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual task.";
    }

    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | %s", super.getSaveTaskString(), deadline.format(READ_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(PRINT_FORMAT));
    }
}
