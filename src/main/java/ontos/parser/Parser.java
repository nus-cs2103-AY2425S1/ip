package ontos.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ontos.commands.Command;
import ontos.exception.OntosException;
import ontos.task.Task;

/**
 * Representing a method used to parse inputs and return the action that the user wants to take.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input The user's input as a String.
     * @return A Command object representing the user's desired action.
     * @throws IllegalArgumentException if the input is invalid or unrecognized.
     * @throws OntosException if there is an issue with the input specific to task creation or manipulation.
     */
    public static Command parse(String input) throws IllegalArgumentException, OntosException {
        assert input != null : "Input cannot be null";
        if (input.equalsIgnoreCase("bye")) {
            return new Command.ByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new Command.ListCommand();
        } else if (input.startsWith("mark")) {
            return parseMark(input);
        } else if (input.startsWith("unmark")) {
            return parseUnmark(input);
        } else if (input.startsWith("delete")) {
            return parseDelete(input);
        } else if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else if (input.startsWith("find")) {
            return parseFind(input);
        } else if (input.equalsIgnoreCase("help")) {
            return new Command.HelpCommand();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses the user input and returns a MarkCommand.
     *
     * @param input The user's input as a String.
     * @return A MarkCommand object representing the user's desired action.
     * @throws OntosException if the input is invalid.
     */
    public static Command parseMark(String input) throws OntosException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new Command.MarkCommand(true, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OntosException("The correct usage of 'mark' is: mark n, where n is a natural number (ℕ).");
        }
    }

    /**
     * Parses the user input and returns an UnmarkCommand.
     *
     * @param input The user's input as a String.
     * @return An UnmarkCommand object representing the user's desired action.
     * @throws OntosException if the input is invalid.
     */
    public static Command parseUnmark(String input) throws OntosException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new Command.MarkCommand(false, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OntosException("The correct usage of 'unmark' is:"
                    + " unmark n, where n is a natural number (ℕ).");
        }
    }

    /**
     * Parses the user input and returns a DeleteCommand.
     *
     * @param input The user's input as a String.
     * @return A DeleteCommand object representing the user's desired action.
     * @throws OntosException if the input is invalid.
     */
    public static Command parseDelete(String input) throws OntosException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new Command.DeleteCommand(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OntosException("The correct usage of 'delete' is:"
                    + " delete n, where n is a natural number (ℕ).");
        }
    }

    /**
     * Parses the user input and returns an AddTaskCommand for a ToDo task.
     *
     * @param input The user's input as a String.
     * @return An AddTaskCommand object representing the user's desired action.
     * @throws OntosException if the input String does not contain a description.
     */
    public static Command parseToDo(String input) throws OntosException {
        Task toDo = null;
        try {
            toDo = Task.toDo(input.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OntosException(" OOPS!!! The description of a todo cannot be empty.");
        }
        return new Command.AddTaskCommand(toDo);
    }

    /**
     * Parses the user input and returns an AddTaskCommand for a Deadline task.
     *
     * @param input The user's input as a String.
     * @return An AddTaskCommand object representing the user's desired action.
     * @throws OntosException if the input String does not contain a description or deadline.
     */
    public static Command parseDeadline(String input) throws OntosException {
        int startOfDesc = input.indexOf(" ");
        int endOfDesc = input.indexOf(" /by");

        if (endOfDesc == -1) {
            throw new OntosException(" OOPS!!! A deadline task requires a deadline.");
        }

        try {
            String description = input.substring(startOfDesc, endOfDesc).trim();
            LocalDate dueBy = LocalDate.parse(input.substring(endOfDesc + 4).trim());

            if (description == "") {
                throw new OntosException(" OOPS!!! The description of a deadline cannot be empty.");
            }

            Task deadline = Task.deadline(description, dueBy);
            return new Command.AddTaskCommand(deadline);
        } catch (OntosException e) {
            throw new OntosException(" OOPS!!! The description of a deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new OntosException(" OOPS!!! An deadline task requires valid deadline.");
        }
    }

    /**
     * Parses the user input and returns an AddTaskCommand for an Event task.
     *
     * @param input The user's input as a String.
     * @return An AddTaskCommand object representing the user's desired action.
     * @throws OntosException if the input String does not contain a description or start and end dates.
     */
    public static Command parseEvent(String input) throws OntosException {
        int startOfDesc = input.indexOf(" ");
        int endOfDesc = input.indexOf(" /from");
        int endOfFrom = input.indexOf(" /to");

        if (endOfDesc == -1 || endOfFrom == -1) {
            throw new OntosException(" OOPS!!! An event task requires a start and end date.");
        }

        try {
            String description = input.substring(startOfDesc, endOfDesc).trim();
            LocalDate start = LocalDate.parse(input.substring(endOfDesc + 6, endOfFrom).trim());
            LocalDate end = LocalDate.parse(input.substring(endOfFrom + 4).trim());

            if (description == "") {
                throw new OntosException(" OOPS!!! The description of an event cannot be empty.");
            }

            if (start.isAfter(end)) {
                throw new OntosException(" OOPS!!! The start date of an event cannot be after the end date.");
            }

            Task event = Task.event(description, start, end);
            return new Command.AddTaskCommand(event);
        } catch (OntosException e) {
            throw new OntosException(" OOPS!!! The description of an event cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new OntosException(" OOPS!!! An event task requires valid a start and end date.");
        }
    }

    /**
     * Parses the user input and returns a FindCommand.
     *
     * @param input The user's input as a String.
     * @return A FindCommand object representing the user's desired action.
     * @throws OntosException if the input is invalid.
     */
    public static Command parseFind(String input) throws IllegalArgumentException, OntosException {
        String searchCriteria = "";
        try {
            searchCriteria = input.split(" ", 2)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OntosException("The correct usage of 'find' is:"
                    + " find n, where n is the keyword you want to search.");
        }
        return new Command.FindCommand(searchCriteria);
    }
}
