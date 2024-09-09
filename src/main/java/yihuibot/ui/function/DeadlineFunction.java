package yihuibot.ui.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.MissingDeadlineException;
import yihuibot.exception.parse.MissingDescriptionException;
import yihuibot.executable.AddTask;
import yihuibot.executable.Executable;
import yihuibot.task.Deadline;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class DeadlineFunction extends Function {
    private String dateTimeFormat;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new DeadlineFunction.
     *
     * @param dateTimeFormat the date time format pattern.
     * @param formatter the formatter for formatting Strings to LocalDateTime objects.
     */
    public DeadlineFunction(String dateTimeFormat, DateTimeFormatter formatter) {
        this.dateTimeFormat = dateTimeFormat;
        this.formatter = formatter;
    }

    /**
     * Return a AddTask Executable that adds a new Deadline Task. Throws ParseExceptions when
     * the description or deadline is not provided as arguments. Also throws ParseExceptions
     * when deadline provided cannot be converted to a LocalDateTime object.
     *
     * @param arguments the list of arguments called with 'deadline'.
     * @return a AddTask Executable.
     * @throws MissingDescriptionException when no description is provided for the Deadline Task.
     * @throws MissingDeadlineException when no deadline is provided for the Deadline Task.
     * @throws InvalidArgumentException when the deadline cannot be converted into a LocalDateTime
     *                                  object.
     */
    @Override
    public Executable call(String... arguments) throws MissingDescriptionException,
            MissingDeadlineException, InvalidArgumentException {
        String sample = "deadline return book /by 2024-04-08 06:30";

        if (arguments == null) {
            throw new MissingDescriptionException(sample);
        }

        int idx = findIndexOfStringInArray(arguments, "/by");

        if (idx == 0) {
            throw new MissingDescriptionException(sample);
        }

        if (idx < 0 || idx == arguments.length - 1) {
            throw new MissingDeadlineException(sample);
        }

        String description = sliceAndJoinAt(arguments, 0, idx, " ");
        String s = sliceAndJoinAt(arguments, idx + 1, arguments.length, " ");
        try {
            LocalDateTime deadline = parseDateTime(s, formatter);
            Deadline task = new Deadline(description, deadline);
            return new AddTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(dateTimeFormat, s);
        }
    }
}
