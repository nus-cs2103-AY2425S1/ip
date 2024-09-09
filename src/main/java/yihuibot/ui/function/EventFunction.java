package yihuibot.ui.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yihuibot.exception.parse.IncorrectArrangementException;
import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.MissingDescriptionException;
import yihuibot.exception.parse.MissingEndTimeException;
import yihuibot.exception.parse.MissingStartTimeException;
import yihuibot.executable.AddTask;
import yihuibot.executable.Executable;
import yihuibot.task.Event;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class EventFunction extends Function {
    private String dateTimeFormat;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new EventFunction.
     *
     * @param dateTimeFormat the date time format pattern.
     * @param formatter the formatter for formatting Strings to LocalDateTime objects.
     */
    public EventFunction(String dateTimeFormat, DateTimeFormatter formatter) {
        this.dateTimeFormat = dateTimeFormat;
        this.formatter = formatter;
    }

    /**
     * Return a AddTask Executable that adds a new Event Task. Throws ParseExceptions when
     * the description, start or end time is not provided as arguments. Also throws ParseExceptions
     * when start or end time provided cannot be converted to a LocalDateTime object.
     *
     * @param arguments the list of arguments called with 'event'.
     * @return a AddTask Executable.
     * @throws MissingDescriptionException when no description is provided for the Event Task.
     * @throws MissingStartTimeException when no start time is provided for the Event Task.
     * @throws MissingEndTimeException when no end time is provided for the Event Task.
     * @throws InvalidArgumentException when the deadline cannot be converted into a LocalDateTime
     *                                  object.
     * @throws IncorrectArrangementException when user input the end time before the start time.
     */
    @Override
    public Executable call(String... arguments) throws MissingDescriptionException,
            MissingStartTimeException, MissingEndTimeException, InvalidArgumentException,
            IncorrectArrangementException {
        String sample = "event project meeting /from 2024-04-08 10:30 /to 2024-04-08 12:30";

        if (arguments == null) {
            throw new MissingDescriptionException(sample);
        }

        int fromIdx = findIndexOfStringInArray(arguments, "/from");
        int toIdx = findIndexOfStringInArray(arguments, "/to");

        if (fromIdx < 0) {
            throw new MissingStartTimeException(sample);
        }

        if (toIdx < 0) {
            throw new MissingEndTimeException(sample);
        }

        if (toIdx < fromIdx) {
            throw new IncorrectArrangementException(sample);
        }

        if (fromIdx == 0) {
            throw new MissingDescriptionException(sample);
        }

        if (toIdx - fromIdx < 2) {
            throw new MissingStartTimeException(sample);
        }

        if (toIdx == arguments.length - 1) {
            throw new MissingEndTimeException(sample);
        }

        String description = sliceAndJoinAt(arguments, 0, fromIdx, " ");
        String s = sliceAndJoinAt(arguments, fromIdx + 1, toIdx, " ");
        try {
            LocalDateTime from = parseDateTime(s, formatter);
            String t = sliceAndJoinAt(arguments, toIdx + 1, arguments.length, " ");
            LocalDateTime to = parseDateTime(t, formatter);
            Event task = new Event(description, from, to);
            return new AddTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(dateTimeFormat);
        }
    }
}
