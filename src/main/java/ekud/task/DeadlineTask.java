package ekud.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import ekud.exceptions.EkudException;

/**
 * Represents a {@link Task} that has a deadline.
 *
 * @author uniqly
 */
public class DeadlineTask extends Task implements IHasDeadline {
    public static final String BY_TOKEN = "/by";

    /** The {@link LocalDateTime} format when parsing input date Strings */
    private static final DateTimeFormatter READ_DATE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm", Locale.ENGLISH)
                    .withResolverStyle(ResolverStyle.STRICT);

    /** The {@link LocalDateTime} format when outputting date Strings */
    private static final DateTimeFormatter PRINT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a", Locale.ENGLISH)
                    .withResolverStyle(ResolverStyle.STRICT);

    private static final String EMPTY_DESCRIPTION_MESSAGE =
        "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual task.";
    private static final String EMPTY_DEADLINE_MESSAGE = """
            Whoopsies!! Looks like you forgot your deadline!
            I'll say this once: next time mark your deadline with '/by'.""";
    private static final String WRONG_DATE_FORMAT_MESSAGE = """
            Whoopsies!! It looks like you tried to pass a deadline that I cannot read!
            I'd recommend that you follow the 'd/M/yyyy HHmm' format. Or else...""";
    private static final String SAVE_STRING_FORMAT = "D | %s | %s";
    private static final String STRING_FORMAT = "[D]%s (by: %s)";

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
            throw new EkudException(EMPTY_DEADLINE_MESSAGE);
        }

        try {
            this.deadline = LocalDateTime.parse(deadline, READ_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new EkudException(WRONG_DATE_FORMAT_MESSAGE);
        }
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return EMPTY_DESCRIPTION_MESSAGE;
    }

    @Override
    public String getSaveTaskString() {
        return String.format(SAVE_STRING_FORMAT, super.getSaveTaskString(),
                deadline.format(READ_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(), deadline.format(PRINT_DATE_FORMAT));
    }
}
