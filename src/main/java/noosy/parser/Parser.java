package noosy.parser;

import noosy.commands.*;
import noosy.exception.NoosyDateException;
import noosy.exception.NoosyDateTimeException;
import noosy.exception.NoosyException;
import noosy.exception.NoosyIncompleteTaskException;
import noosy.task.Deadline;
import noosy.task.Event;
import noosy.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and creates appropriate Command objects for the task management system.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string input by the user.
     * @return A Command object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parse(String fullCommand) throws NoosyException {

        assert !fullCommand.isBlank() : "Command line cannot be empty.";

        String[] separated = fullCommand.split(" ", 2);
        String firstWord = separated[0];
        String input = separated.length > 1 ? separated[1] : null;

        switch (firstWord) {
            case "bye":
                return parseBye();

            case "list":
                return parseList();

            case "mark":
                return parseMark(input);

            case "unmark":
                return parseUnmark(input);

            case "delete":
                return parseDelete(input);

            case "find":
                return parseFind(input);

            case "on":
                return parseDateSearch(input);

            case "todo":
                return parseTodo(input);

            case "deadline":
                return parseDeadline(input);

            case "event":
                return parseEvent(input);

            default:
                String help = "Beep Boop. Noosy is confused... \n" +
                        "Noosy only understands: \n" +
                        "list – listing your tasks \n" +
                        "mark i– mark task i as complete \n" +
                        "unmark i – unmark task i \n" +
                        "delete i – delete task i \n" +
                        "todo – add a task with no time associated \n" +
                        "deadline – add a task with a deadline \n" +
                        "event – add a task with duration \n" +
                        "on – check tasks on a certain date \n" +
                        "bye – say goodbye to Noosy \n";
                throw new NoosyException(help);
        }
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @return An ExitCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseBye() {
        return new ExitCommand();
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @return An ListCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseList() {
        return new ListCommand();
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param taskNum The task number to be marked.
     * @return A MarkCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseMark(String taskNum) throws NoosyException {
        if (taskNum == null) {
            throw new NoosyException("Which task do you want to mark?");
        }

        return new MarkCommand(Integer.parseInt(taskNum.trim()) - 1);
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param taskNum The task number to be unmarked.
     * @return An UnmarkCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseUnmark(String taskNum) throws NoosyException {
        if (taskNum == null) {
            throw new NoosyException("Which task do you want to unmark?");
        }

        return new UnmarkCommand(Integer.parseInt(taskNum.trim()) - 1);
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param taskNum The task number to be deleted.
     * @return A DeleteCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseDelete(String taskNum) throws NoosyException {
        if (taskNum == null) {
            throw new NoosyException("Which task do you want to delete?");
        }

        return new DeleteCommand(Integer.parseInt(taskNum.trim()) - 1);
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The keyword of the task(s) to be found.
     * @return A FindCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseFind(String input) throws NoosyException {
        if (input == null) {
            throw new NoosyException("I can't see the keyword. Try typing it.");
        }

        return new FindCommand(input);
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The date of the task(s) to be found.
     * @return A DateSearchCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseDateSearch(String input) throws NoosyException {
        if (input == null) {
            throw new NoosyException("You forgot the date! \n" +
                    "It should be formatted as: yyyy-MM-dd");
        }

        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new DateSearchCommand(date);
        } catch (DateTimeParseException e) {
            throw new NoosyDateException();
        }
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The description of the task to be created.
     * @return A TodoCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseTodo(String input) throws NoosyException {
        if (input == null) {
            throw new NoosyIncompleteTaskException();
        }

        return new AddCommand(new Todo(input));
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The description and deadline of the task to be created.
     * @return A DeadlineCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseDeadline(String input) throws NoosyException {
        if (input == null) {
            throw new NoosyIncompleteTaskException();
        }

        String[] withDue = input.split(" /by ");
        if (withDue.length < 2) {
            throw new NoosyException("I think you forgot the deadline. \n" +
                    "Remember to type in the task and /by the deadline!");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(withDue[1], formatter);
            return new AddCommand(new Deadline(withDue[0], deadline));
        } catch (DateTimeException e) {
            throw new NoosyDateException();
        }
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The description, start time and end time of the task to be created.
     * @return An EventCommand object corresponding to the user's input.
     * @throws NoosyException If the input is invalid or incomplete.
     */
    public static Command parseEvent(String input) throws NoosyException {
        if (input == null) {
            throw new NoosyIncompleteTaskException();
        }

        String[] withDuration = input.split(" /from | /to ");
        if (withDuration.length < 3) {
            throw new NoosyException("I think you forgot the duration. \n" +
                    "Remember to type in the task and /from the start /to the end!");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(withDuration[1], formatter);
            LocalDateTime to = LocalDateTime.parse(withDuration[2], formatter);
            return new AddCommand(new Event(withDuration[0], from, to));
        } catch (DateTimeException e) {
            throw new NoosyDateTimeException();
        }
    }

}
