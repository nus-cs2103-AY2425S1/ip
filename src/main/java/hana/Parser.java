package hana;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hana.task.Deadline;
import hana.task.Event;
import hana.task.ToDo;

/**
 * Handles the parsing of user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user's command input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string input by the user.
     * @return A Command object representing the user's command.
     * @throws HanaException If the command is invalid or contains errors.
     */
    public static Command parse(String fullCommand) throws HanaException {
        if (fullCommand == null || fullCommand.isEmpty()) {
            throw new HanaException("You must specify a command");
        }
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        switch (commandWord) {
        case "list":
            return handleList();
        case "mark":
            return handleMark(fullCommand);
        case "unmark":
            return handleUnmark(fullCommand);
        case "todo":
            return handleToDo(parts);
        case "deadline":
            return handleDeadline(fullCommand, parts);
        case "event":
            return handleEvent(fullCommand, parts);
        case "delete":
            return handleDelete(fullCommand);
        case "find":
            return handleFind(parts);
        case "massMark":
            return handleMassMark(parts);
        case "massUnmark":
            return handleMassUnmark(parts);
        case "massDelete":
            return handleMassDelete(parts);
        case "bye":
            return handleExit();
        default:
            throw new HanaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles the creation of a ListCommand to list all tasks.
     *
     * @return A new ListCommand instance.
     */
    private static ListCommand handleList() {
        return new ListCommand();
    }

    /**
     * Handles the creation of an ExitCommand to exit the program.
     *
     * @return A new ExitCommand instance.
     */
    private static ExitCommand handleExit() {
        return new ExitCommand();
    }

    /**
     * Handles the creation of a MassDeleteCommand based on the user input.
     *
     * @param parts The array containing the command and the keyword.
     * @return A new MassDeleteCommand instance with the provided keyword.
     * @throws HanaException If the keyword is missing or invalid.
     */
    private static MassDeleteCommand handleMassDelete(String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! You must provide a keyword for massDelete.");
        }
        assert parts.length == 2 : "Find command should have a keyword";
        return new MassDeleteCommand(parts[1]);
    }

    /**
     * Handles the creation of a MassUnmarkCommand based on the user input.
     *
     * @param parts The array containing the command and the keyword.
     * @return A new MassUnmarkCommand instance with the provided keyword.
     * @throws HanaException If the keyword is missing or invalid.
     */
    private static MassUnmarkCommand handleMassUnmark(String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! You must provide a keyword for massUnmark.");
        }
        assert parts.length == 2 : "Find command should have a keyword";
        return new MassUnmarkCommand(parts[1]);
    }

    /**
     * Handles the creation of a MassMarkCommand based on the user input.
     *
     * @param parts The array containing the command and the keyword.
     * @return A new MassMarkCommand instance with the provided keyword.
     * @throws HanaException If the keyword is missing or invalid.
     */
    private static MassMarkCommand handleMassMark(String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! You must provide a keyword for massMark.");
        }
        assert parts.length == 2 : "Find command should have a keyword";
        return new MassMarkCommand(parts[1]);
    }

    /**
     * Handles the creation of a FindCommand based on the user input.
     *
     * @param parts The array containing the command and the keyword.
     * @return A new FindCommand instance with the provided keyword.
     * @throws HanaException If the search keyword is missing or invalid.
     */
    private static FindCommand handleFind(String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! The search keyword cannot be empty.");
        }
        assert parts.length == 2 : "Find command should have a keyword";
        return new FindCommand(parts[1]);
    }

    /**
     * Handles the creation of a DeleteCommand based on the user input.
     *
     * @param fullCommand The full user input command string.
     * @return A new DeleteCommand instance to delete the specified task.
     * @throws HanaException If the task index is invalid or the delete syntax is incorrect.
     */
    private static DeleteCommand handleDelete(String fullCommand) throws HanaException {
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(fullCommand);
        if (!deleteMatcher.matches()) {
            throw new HanaException("Invalid delete syntax. Write only the task index after the word 'delete'.");
        }
        int taskNumber = Integer.parseInt(deleteMatcher.group(1));
        assert taskNumber >= 0 : "Task number should be non-negative";
        int indexNumber = taskNumber - 1;
        return new DeleteCommand(indexNumber);
    }

    /**
     * Handles the creation of an AddCommand for an event based on the user input.
     *
     * @param fullCommand The full user input command string.
     * @param parts The array containing the event details.
     * @return A new AddCommand instance to add the event.
     * @throws HanaException If the event description or time is invalid.
     */
    private static AddCommand handleEvent(String fullCommand, String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventParts = parts[1].split(" /from | /to ");
        if (fullCommand.trim().equals("event") || fullCommand.startsWith("event /from")) {
            throw new HanaException("OOPS!!! The description of an event cannot be empty.");
        } else if (!fullCommand.contains(" /from ") || !fullCommand.contains(" /to ")) {
            throw new HanaException("OOPS!!! Please add event time by adding /from and /to");
        } else if (fullCommand.indexOf(" /to ") < fullCommand.indexOf(" /from ")) {
            throw new HanaException("OOPS!!! Please add /from before /to");
        }
        try {
            LocalDate dateFrom = LocalDate.parse(eventParts[1]);
            LocalDate dateTo = LocalDate.parse(eventParts[2]);
            if (dateFrom.isAfter(dateTo)) {
                throw new HanaException("OOPS!!! from date cannot be after to date.");
            }
            assert !dateFrom.isAfter(dateTo) : "Event start date cannot be after end date";
            eventParts[1] = dateFrom.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            eventParts[2] = dateTo.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new HanaException("OOPS!!! The date format is incorrect. \nPlease use the format YYYY-MM-DD.");
        }
        return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    /**
     * Handles the creation of an AddCommand for a deadline based on the user input.
     *
     * @param fullCommand The full user input command string.
     * @param parts The array containing the deadline details.
     * @return A new AddCommand instance to add the deadline.
     * @throws HanaException If the deadline description or time is invalid.
     */
    private static AddCommand handleDeadline(String fullCommand, String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = parts[1].split(" /by ");
        if (fullCommand.trim().equals("deadline") || fullCommand.startsWith("deadline /by")) {
            throw new HanaException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!fullCommand.contains(" /by ")) {
            throw new HanaException("OOPS!!! Please add deadline date/time by adding /by");
        }
        try {
            LocalDate dateBy = LocalDate.parse(deadlineParts[1]);
            deadlineParts[1] = dateBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new HanaException("OOPS!!! The date format is incorrect. Please use the format YYYY-MM-DD.");
        }
        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    /**
     * Handles the creation of an AddCommand for a todo task based on the user input.
     *
     * @param parts The array containing the todo description.
     * @return A new AddCommand instance to add the todo task.
     * @throws HanaException If the todo description is empty.
     */
    private static AddCommand handleToDo(String[] parts) throws HanaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(parts[1]));
    }

    /**
     * Handles the creation of an UnmarkCommand based on the user input.
     *
     * @param fullCommand The full user input command string.
     * @return A new UnmarkCommand instance to unmark the specified task.
     * @throws HanaException If the task index is invalid or the unmark syntax is incorrect.
     */
    private static UnmarkCommand handleUnmark(String fullCommand) throws HanaException {
        Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");
        Matcher unmarkMatcher = unmarkPattern.matcher(fullCommand);
        if (!unmarkMatcher.matches()) {
            throw new HanaException("Invalid unmark syntax. Write only the task index after the word 'unmark'.");
        }
        int taskNumber = Integer.parseInt(unmarkMatcher.group(1));
        assert taskNumber >= 0 : "Task number should be non-negative";
        int indexNumber = taskNumber - 1;
        return new UnmarkCommand(indexNumber);
    }

    /**
     * Handles the creation of a MarkCommand based on the user input.
     *
     * @param fullCommand The full user input command string.
     * @return A new MarkCommand instance to mark the specified task as done.
     * @throws HanaException If the task index is invalid or the mark syntax is incorrect.
     */
    private static MarkCommand handleMark(String fullCommand) throws HanaException {
        Pattern markPattern = Pattern.compile("^mark (\\d+)$");
        Matcher markMatcher = markPattern.matcher(fullCommand);
        if (!markMatcher.matches()) {
            throw new HanaException("Invalid mark syntax. Write only the task index after the word 'mark'.");
        }
        int taskNumber = Integer.parseInt(markMatcher.group(1));
        assert taskNumber >= 0 : "Task number should be non-negative";
        int indexNumber = taskNumber - 1;
        return new MarkCommand(indexNumber);
    }
}

