package ekud.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import ekud.exceptions.EkudException;

/**
 * Represents a {@link Task} that spans over two dates.
 *
 * @author uniqly
 */
public class EventTask extends Task implements IHasDeadline {
    public static final String FROM_TOKEN = "/from";
    public static final String TO_TOKEN = "/to";

    /** The {@link LocalDateTime} format when parsing input date Strings */
    private static final DateTimeFormatter READ_DATE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);

    /** The {@link LocalDateTime} format when outputting date Strings */
    private static final DateTimeFormatter PRINT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH);

    private static final String EMPTY_DESCRIPTION_MESSAGE =
        "Did you forget your EVENT?\nBecause you tried to make an event of nothing!";
    private static final String EMPTY_FROM_MESSAGE = """
            Woah Woah! Calm down buddy!
            Could you first tell when this event starts using '/from'?""";
    private static final String EMPTY_TO_MESSAGE = """
            Dude, stop being overzealous! Surely this event doesn't last forever?
            Think hard about it and you can tell me when it ends again with '/to'.""";
    private static final String WRONG_DATE_FORMAT_MESSAGE = """
            Man... What's wrong with you!!
            Why can't you just follow the correct 'd/M/yyyy HHmm' date format!""";
    private static final String START_BEFORE_END_MESSAGE = """
            You might have missed it, but the start of the event must before the end!
              %s is NOT before %s!!""";
    private static final String SAVE_STRING_FORMAT = "E | %s | %s | %s";
    private static final String STRING_FORMAT = "[E]%s (from: %s to: %s)";

    /** The start date of the event */
    protected LocalDateTime from;

    /** The end date of the event */
    protected LocalDateTime to;

    /**
     * Constructs the event {@link Task} that spends over two days.
     *
     * @param description The description of the event.
     * @param from A {@link String} representing the start date.
     * @param to A {@link String} representing the end date.
     * @throws EkudException If any inputs are empty, or {@code from} or {@code to} has the wrong date format.
     */
    public EventTask(String description, String from, String to) throws EkudException {
        super(description);
        // check for empty strings
        if (from == null || from.isEmpty()) {
            throw new EkudException(EMPTY_FROM_MESSAGE);
        } else if (to == null || to.isEmpty()) {
            throw new EkudException(EMPTY_TO_MESSAGE);
        }

        // try to parse dates
        try {
            this.from = LocalDateTime.parse(from, READ_DATE_FORMAT);
            this.to = LocalDateTime.parse(to, READ_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new EkudException(WRONG_DATE_FORMAT_MESSAGE);
        }

        // check for date errors
        if (this.from.isAfter(this.to)) {
            throw new EkudException(
                    String.format(START_BEFORE_END_MESSAGE,
                            this.from.format(PRINT_DATE_FORMAT),
                            this.to.format(PRINT_DATE_FORMAT)));
        }
    }

    @Override
    public LocalDateTime getDeadline() {
        return to;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return EMPTY_DESCRIPTION_MESSAGE;
    }

    @Override
    public String getSaveTaskString() {
        return String.format(SAVE_STRING_FORMAT, super.getSaveTaskString(),
                from.format(READ_DATE_FORMAT), to.format(READ_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(),
                from.format(PRINT_DATE_FORMAT), to.format(PRINT_DATE_FORMAT));
    }
}
