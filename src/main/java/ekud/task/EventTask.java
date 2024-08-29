package ekud.task;

import ekud.exceptions.EkudException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a {@link Task} that spans over two dates.
 *
 * @author uniqly
 */
public class EventTask extends Task {
    /** The {@link LocalDateTime} format when parsing input date Strings */
    public static DateTimeFormatter READ_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);

    /** The {@link LocalDateTime} format when outputting date Strings */
    public static DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH);

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
        if (from == null || from.isEmpty()) {
            throw new EkudException("""
                    Woah Woah! Calm down buddy!
                    Could you first tell when this event starts using '/from'?""");
        } else if (to == null || to.isEmpty()) {
            throw new EkudException("""
                    Dude, stop being overzealous! Surely this event doesn't last forever?
                    Think hard about it and you can tell me when it ends again with '/to'.""");
        }

        try {
            this.from = LocalDateTime.parse(from, READ_FORMAT);
            this.to = LocalDateTime.parse(to, READ_FORMAT);
        } catch (DateTimeParseException e) {
            throw new EkudException("""
                    Man... What's wrong with you!!
                    Why can't you just follow the correct 'd/M/yyyy HHmm' date format!""");
        }
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "Did you forget your EVENT?\nBecause you tried to make an event of nothing!";
    }

    @Override
    public String getSaveTaskString() {
        return String.format("E | %s | %s | %s", super.getSaveTaskString(),
                from.format(READ_FORMAT), to.format(READ_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(PRINT_FORMAT), to.format(PRINT_FORMAT));
    }
}
