package yihuibot.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import yihuibot.exception.parse.CommandNotFoundException;
import yihuibot.exception.parse.IncorrectArrangementException;
import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.MissingDeadlineException;
import yihuibot.exception.parse.MissingDescriptionException;
import yihuibot.exception.parse.MissingEndTimeException;
import yihuibot.exception.parse.MissingStartTimeException;
import yihuibot.exception.parse.ParseException;
import yihuibot.exception.parse.TooLittleArgumentsException;
import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.AddTask;
import yihuibot.executable.DeleteTask;
import yihuibot.executable.Executable;
import yihuibot.executable.Exit;
import yihuibot.executable.FilterTask;
import yihuibot.executable.ListTask;
import yihuibot.executable.MarkTask;
import yihuibot.executable.UnmarkTask;
import yihuibot.task.Deadline;
import yihuibot.task.Event;
import yihuibot.task.Todo;

/**
 * A parser to parse user's input from YihuiBot, returning the appropriate executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Parser {
    private String dateTimeFormat;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new Parser. Takes in a string pattern for formatting
     * date time strings.
     *
     * @param dateTimeFormat the date time format pattern.
     * @throws IllegalArgumentException if the pattern is not valid.
     */
    public Parser(String dateTimeFormat) throws IllegalArgumentException {
        this.dateTimeFormat = dateTimeFormat;
        formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
    }

    /**
     * Parse the user's input, breaking it down into a command and an array of
     * arguments. Based on what command and arguments were given, the Parser will
     * instantiate the appropriate executable with the appropriate arguments. This
     * executable can then be separately executed to get its output.
     *
     * @param input the user's input.
     * @return the appropriate executable instantiated with the arguments.
     * @throws NullPointerException when input is null.
     * @throws ParseException when error occurred during parsing (e.g. incorrect arguments).
     */
    public Executable parse(String input) throws NullPointerException, ParseException {
        if (input == null) {
            throw new NullPointerException("User's input cannot be null.");
        }

        String[] inputArray = input.split(" ");
        String command = inputArray.length < 1 ? "" : inputArray[0];
        String[] arguments = inputArray.length < 2
                ? null
                : Arrays.copyOfRange(inputArray, 1, inputArray.length);

        switch (command) {
        case "bye":
            return bye(arguments);
        case "list":
            return list(arguments);
        case "mark":
            return mark(arguments);
        case "unmark":
            return unmark(arguments);
        case "todo":
            return todo(arguments);
        case "deadline":
            return deadline(arguments);
        case "event":
            return event(arguments);
        case "delete":
            return delete(arguments);
        case "find":
            return find(arguments);
        default:
            throw new CommandNotFoundException(input);
        }
    }

    /**
     * Return an Exit Executable if there no arguments are provided. Throws
     * TooManyArgumentsException when 'bye' is called with arguments.
     *
     * @param arguments the list of arguments called with 'bye'.
     * @return an Exit Executable.
     * @throws TooManyArgumentsException when command is called with any arguments.
     */
    private Executable bye(String... arguments) throws TooManyArgumentsException {
        if (arguments != null) {
            throw new TooManyArgumentsException(0);
        }
        return new Exit();
    }

    /**
     * Return a ListTask Executable if there no arguments are provided. Throws
     * TooManyArgumentsException when 'list' is called with arguments.
     *
     * @param arguments the list of arguments called with 'list'.
     * @return an ListTask Executable.
     * @throws TooManyArgumentsException when command is called with any arguments.
     */
    private Executable list(String... arguments) throws TooManyArgumentsException {
        if (arguments != null) {
            throw new TooManyArgumentsException(0);
        }
        return new ListTask();
    }

    /**
     * Return a MarkTask Executable if 'mark' is called with an integer. Otherwise,
     * throws ParseExceptions.
     *
     * @param arguments the list of arguments called with 'mark'.
     * @return a MarkTask Executable.
     * @throws TooLittleArgumentsException if 'mark' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'mark' is not an integer.
     */
    private Executable mark(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "mark 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new MarkTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }

    /**
     * Return an UnmarkTask Executable if 'unmark' is called with an integer. Otherwise,
     * throws ParseException.
     *
     * @param arguments the list of arguments called with 'unmark'.
     * @return a UnmarkTask Executable.
     * @throws TooLittleArgumentsException when 'unmark' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'unmark' is not an integer.
     */
    private Executable unmark(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "unmark 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new UnmarkTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }

    /**
     * Return a DeleteTask Executable if 'delete' is called with an integer. Otherwise,
     * throws ParseException.
     *
     * @param arguments the list of arguments called with 'delete'.
     * @return a DeleteTask Executable.
     * @throws TooLittleArgumentsException when 'delete' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'delete' is not an integer.
     */
    private Executable delete(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "delete 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new DeleteTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }

    /**
     * Return a AddTask Executable that adds a new Todo Task. Throws MissingDescriptionException
     * when no description for the Task is provided.
     *
     * @param arguments the list of arguments called with 'todo'.
     * @return a AddTask Executable.
     * @throws MissingDescriptionException when no description is provided for the Todo Task.
     */
    private Executable todo(String... arguments) throws MissingDescriptionException {
        String sample = "todo read book";

        if (arguments == null || arguments.length < 1) {
            throw new MissingDescriptionException(sample);
        }

        String description = String.join(" ", arguments);
        Todo task = new Todo(description);
        return new AddTask(task);
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
    private Executable deadline(String... arguments) throws MissingDescriptionException,
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

        assert idx > 0 && idx < arguments.length - 1 : "Unexpected value of idx";

        String description = sliceAndJoinAt(arguments, 0, idx, " ");
        String s = sliceAndJoinAt(arguments, idx + 1, arguments.length, " ");
        try {
            LocalDateTime deadline = parseDateTime(s);
            Deadline task = new Deadline(description, deadline);
            return new AddTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(dateTimeFormat, s);
        }
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
    private Executable event(String... arguments) throws MissingDescriptionException,
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

        assert fromIdx > 0 && fromIdx < toIdx : "Unexpected value of fromIdx";
        assert toIdx > fromIdx && toIdx < arguments.length - 1 : "Unexpected value of fromIdx";

        String description = sliceAndJoinAt(arguments, 0, fromIdx, " ");
        String s = sliceAndJoinAt(arguments, fromIdx + 1, toIdx, " ");
        try {
            LocalDateTime from = parseDateTime(s);
            String t = sliceAndJoinAt(arguments, toIdx + 1, arguments.length, " ");
            LocalDateTime to = parseDateTime(t);
            Event task = new Event(description, from, to);
            return new AddTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(dateTimeFormat);
        }
    }

    /**
     * Return a FilterTask Executable to filter the Tasks based on the arguments.
     *
     * @param arguments the list of arguments called with 'find'.
     * @return a FilterTask Executable.
     * @throws TooLittleArguments if no arguments are called with 'find'.
     */
    private Executable find(String... arguments) throws TooLittleArgumentsException {
        if (arguments == null || arguments.length < 1) {
            throw new TooLittleArgumentsException(1);
        }
        String filter = String.join(" ", arguments);
        return new FilterTask(filter);
    }

    /**
     * Find and return the index of String s in a String array.
     *
     * @param array the string array to be searched.
     * @param s the string to search for.
     * @return the index of the String s in given array.
     *         Return -1 if no such String is found.
     */
    private int findIndexOfStringInArray(String[] array, String s) {
        return Arrays.<String>asList(array).indexOf(s);
    }

    /**
     * Slice the array with range from and to, and then join the remaining array using
     * the given delimiter.
     *
     * @param array the string array to slice.
     * @param from the index of the array to slice from, inclusive.
     * @param to the index of the array to slice to, exclusive.
     * @param delimiter the delimiter that separates each element.
     * @return the resulting String after joining it using delimiter.
     */
    private String sliceAndJoinAt(String[] array, int from, int to, CharSequence delimiter) {
        return String.join(delimiter, Arrays.<String>copyOfRange(array, from, to));
    }

    /**
     * Converts the String into a LocalDateTime object.
     *
     * @param dateTime the dateTime in String.
     * @return a LocalDateTime object.
     * @throws DateTimeParseException when the string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
