package lexi.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import lexi.command.AddCommand;
import lexi.command.ByeCommand;
import lexi.command.Command;
import lexi.command.Commands;
import lexi.command.DeleteCommand;
import lexi.command.FindCommand;
import lexi.command.ListCommand;
import lexi.command.MarkCommand;
import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Todo;

/**
 * The Parser class handles the parsing of user input commands.
 * It interprets the commands and returns the corresponding Command objects.
 */
public class Parser {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param response The user's input command as a string.
     * @return The Command object that corresponds to the user's input.
     * @throws LexiException If the input command is unrecognized or incorrectly formatted.
     */
    public static Command parse(String response) throws LexiException {
        assert response != null : "Input response cannot be null";

        try {
            if (response.isBlank()) {
                throw new LexiException("Please enter one of the following commands:\n" + Commands.printCommands());
            }

            String[] parts = response.split(" ");
            assert parts.length > 0 : "Command must have at least one part";

            Commands command = Commands.valueOf(parts[0].toUpperCase());
            return switch (command) {
            case MARK, UNMARK -> handleMark(parts);
            case TODO -> handleTodo(response);
            case DEADLINE -> handleDeadline(response);
            case EVENT -> handleEvent(response);
            case DELETE -> handleDelete(parts);
            case LIST -> listTasks();
            case FIND -> handleFind(parts);
            case BYE -> handleBye();
            };
        } catch (IllegalArgumentException e) {
            throw new LexiException("Please enter one of the following commands:\n" + Commands.printCommands());
        }
    }
    private static FindCommand handleFind(String[] parts) throws LexiException {
        assert parts != null && parts.length > 1 : "Find command must have a query part";
        String query = Arrays.stream(parts).skip(1).findFirst().orElseThrow(() ->
                new LexiException("Find command requires a query string."));
        return new FindCommand(query);
    }

    /**
     * Handles the "bye" command, which exits the application.
     *
     * @return A ByeCommand object that signals the application to exit.
     */
    private static ByeCommand handleBye() {
        return new ByeCommand();
    }

    /**
     * Handles the "delete" command, which deletes a task.
     *
     * @param parts The parsed user input split into parts.
     * @return A DeleteCommand object with the specified task number.
     * @throws LexiException If the command format is incorrect or if the task number is invalid.
     */
    private static DeleteCommand handleDelete(String[] parts) throws LexiException {
        assert parts != null && parts.length == 2 : "Delete command must have exactly two parts";
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new LexiException("Please enter a valid task number as follows:\n"
                    + "\"delete <task number>\"\n");
        }
    }

    /**
     * Handles the "event" command, which adds an event with a start and end time.
     *
     * @param response The user's input command as a string.
     * @return An AddCommand object with the specified Event.
     * @throws LexiException If the command format is incorrect or if the date/time format is invalid.
     */
    private static AddCommand handleEvent(String response) throws LexiException {
        assert response != null : "Response cannot be null";
        String[] parts = response.split(" /from ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"event <task> /from <date and time> /to <date and time>\"\n"
                + "\"<date> in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        if (parts[0].equals(response)) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(6);
        if (parts.length < 2) {
            throw new LexiException(errorMessage);
        }
        String[] range = parts[1].split(" /to ");
        if (parts[1].equals(range[0])) {
            throw new LexiException(errorMessage);
        }
        String start = range[0];
        String end = range[1];
        try {
            LocalDateTime from = LocalDateTime.parse(start, Parser.inputFormatter);
            LocalDateTime to = LocalDateTime.parse(end, Parser.inputFormatter);
            return new AddCommand(new Event(taskName, from, to));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
                    + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    /**
     * Handles the "deadline" command, which adds a task with a deadline.
     *
     * @param response The user's input command as a string.
     * @return An AddCommand object with the specified Deadline.
     * @throws LexiException If the command format is incorrect or if the date/time format is invalid.
     */
    private static AddCommand handleDeadline(String response) throws LexiException {
        assert response != null : "Response cannot be null";
        String[] parts = response.split(" /by ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"deadline <task> /by <date>\"\n"
                + "\"date and time in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        if (parts[0].equals(response) || parts.length != 2) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(9);
        String dateTime = parts[1];
        try {
            LocalDateTime by = LocalDateTime.parse(dateTime, Parser.inputFormatter);
            return new AddCommand(new Deadline(taskName, by));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
                    + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    /**
     * Handles the "todo" command, which adds a simple todo task.
     *
     * @param response The user's input command as a string.
     * @return An AddCommand object with the specified Todo task.
     * @throws LexiException If the command format is incorrect or if the description is empty.
     */
    private static AddCommand handleTodo(String response) throws LexiException {
        assert response != null : "Response cannot be null";
        String errorMessage = "Sorry! The description of a todo cannot be empty\n"
                + "Please write in this format \"todo <description>\"\n";
        if (response.length() < 6) {
            throw new LexiException(errorMessage);
        }

        String taskName = response.substring(5).trim();

        if (taskName.isEmpty()) {
            throw new LexiException(errorMessage);
        }
        return new AddCommand(new Todo(taskName));
    }

    /**
     * Handles the "mark" and "unmark" commands, which mark or unmark a task as done or not done.
     *
     * @param parts The parsed user input split into parts.
     * @return A MarkCommand object to mark or unmark the specified task.
     * @throws LexiException If the command format is incorrect or if the task number is invalid.
     */
    private static MarkCommand handleMark(String[] parts) throws LexiException {
        int taskNumber = Arrays.stream(parts).skip(1).findFirst()
                .map(part -> Integer.parseInt(part) - 1)
                .orElseThrow(() -> new LexiException("Please enter a valid task number in the format:\n"
                        + "\"mark <number>\""));

        return new MarkCommand(taskNumber, "mark".equalsIgnoreCase(parts[0]));
    }

    /**
     * Handles the "list" command, which lists all tasks.
     *
     * @return A ListCommand object to list all tasks.
     */
    public static ListCommand listTasks() {
        return new ListCommand();
    }
}
