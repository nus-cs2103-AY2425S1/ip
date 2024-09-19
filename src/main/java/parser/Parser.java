package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.FindCommand;
import command.InvalidIndexCommandException;
import command.ListCommand;
import command.MarkCommand;
import command.UnknownCommandException;
import command.UnmarkCommand;
import ouiouibaguette.OuiOuiBaguetteException;
import tasks.DeadlineException;
import tasks.EventException;
import tasks.ToDoException;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding Command object.
     *
     * @param cmd The user input string representing the command.
     * @return The corresponding Command object to be executed.
     * @throws OuiOuiBaguetteException If the command is invalid or any part of the parsing process fails.
     */
    public Command parseCommand(String cmd) throws OuiOuiBaguetteException {
        // Check if cmd is null
        assert cmd != null : "Command cannot be null";

        Command cmdFound = null;

        // Find appropriate command
        cmdFound = cmdFound == null ? parseIfByeCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfListCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfMarkCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfUnmarkCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfTodoCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfDeadlineCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfEventCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfDeleteCommand(cmd) : cmdFound;
        cmdFound = cmdFound == null ? parseIfFindCommand(cmd) : cmdFound;

        if (cmdFound == null) {
            // Unknown command
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }

        return cmdFound;
    }

    /**
     * Parses the command if it is a bye command.
     *
     * @param cmd The user input string representing the command.
     * @return A ByeCommand object or null if the command does not match.
     */
    public Command parseIfByeCommand(String cmd) {
        if (!cmd.equals("bye")) {
            return null;
        }

        return new ByeCommand();
    }

    /**
     * Parses the command if it is a list command.
     *
     * @param cmd The user input string representing the command.
     * @return A ListCommand object or null if the command does not match.
     */
    public Command parseIfListCommand(String cmd) {
        if (!cmd.equals("list")) {
            return null;
        }

        return new ListCommand();
    }

    /**
     * Parses the command if it is a mark command.
     *
     * @param cmd The user input string representing the command.
     * @return A MarkCommand object or null if the command does not match.
     */
    public Command parseIfMarkCommand(String cmd) throws InvalidIndexCommandException {
        if (!cmd.startsWith("mark ")) {
            return null;
        }

        // Parse command to get the index of the task
        if (cmd.length() <= ("mark ").length()) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }

        try {
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }
    }

    /**
     * Parses the command if it is an unmark command.
     *
     * @param cmd The user input string representing the command.
     * @return An UnmarkCommand object or null if the command does not match.
     */
    public Command parseIfUnmarkCommand(String cmd) throws InvalidIndexCommandException {
        if (!cmd.startsWith("unmark ")) {
            return null;
        }

        // Parse command to get the index of the task
        if (cmd.length() <= ("unmark ").length()) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }

        try {
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }
    }

    /**
     * Parses the command if it is a todo command.
     *
     * @param cmd The user input string representing the command.
     * @return An AddToDoCommand object or null if the command does not match.
     * @throws ToDoException If the description of the to-do task is empty.
     */
    public Command parseIfTodoCommand(String cmd) throws ToDoException {
        if (!cmd.startsWith("todo ")) {
            return null;
        }

        // Add ToDo
        if (cmd.length() <= ("todo ").length()) {
            throw new ToDoException("The description of a todo cannot be empty.");
        }

        String desc = cmd.substring(("todo ").length());

        return new AddToDoCommand(desc);
    }

    /**
     * Parses the command if it is a deadline command.
     *
     * @param cmd The user input string representing the command.
     * @return An AddDeadlineCommand object or null if the command does not match.
     * @throws DeadlineException If the description or due date of the deadline is invalid.
     */
    public Command parseIfDeadlineCommand(String cmd) throws DeadlineException {
        if (!cmd.startsWith("deadline ")) {
            return null;
        }

        // Add Deadline
        if (cmd.length() <= ("deadline ").length()) {
            throw new DeadlineException("The description of a deadline cannot be empty.");
        }

        String descAndDate = cmd.substring(("deadline ").length());

        // Check if the format is correct
        if (!descAndDate.contains(" /by ")) {
            throw new DeadlineException("""
                The format entered is wrong.
                \t Please follow the format: deadline <description> /by <due date>""");
        }

        String desc = descAndDate.split(" /by ")[0];
        // Check if there is a valid description
        if (desc.length() == 0) {
            throw new DeadlineException("The description of a deadline cannot be empty.");
        }

        // Check if there is a valid due date
        if (descAndDate.split(" /by ").length < 2) {
            throw new DeadlineException("The due date of a deadline cannot be empty.");
        }
        String date = descAndDate.split(" /by ")[1];

        return new AddDeadlineCommand(desc, date);
    }

    /**
     * Parses the command if it is an event command.
     *
     * @param cmd The user input string representing the command.
     * @return An AddEventCommand object or null if the command does not match.
     * @throws EventException If the description, start date, or end date of the event is invalid.
     */
    public Command parseIfEventCommand(String cmd) throws EventException {
        if (!cmd.startsWith("event")) {
            return null;
        }

        // Add Event
        if (cmd.length() <= ("event ").length()) {
            throw new EventException("The description of a deadline cannot be empty.");
        }

        String descAndStartEnd = cmd.substring(("event ").length());
        // Check if the format is correct
        if (!descAndStartEnd.contains(" /from ") || !descAndStartEnd.contains(" /to ")) {
            throw new EventException("""
                The format entered is wrong.
                \t Please follow the format: event <description> /from <start> /to <end>""");
        }

        String desc = descAndStartEnd.split(" /from ")[0];
        // Check if there is a valid description
        if (desc.length() == 0) {
            throw new EventException("The description of an event cannot be empty.");
        }

        // Check if there is a valid start
        if (descAndStartEnd.indexOf("/from ") + ("/from ").length()
                >= descAndStartEnd.indexOf(" /to")) {
            throw new EventException("The start of an event cannot be empty.");
        }
        String start = descAndStartEnd.substring(
                descAndStartEnd.indexOf("/from ") + ("/from ").length(),
                descAndStartEnd.indexOf(" /to"));

        // Check if there is a valid end
        if (descAndStartEnd.split(" /to ").length < 2) {
            throw new EventException("The end of an event cannot be empty.");
        }
        String end = descAndStartEnd.split(" /to ")[1];

        return new AddEventCommand(desc, start, end);
    }

    /**
     * Parses the command if it is a delete command.
     *
     * @param cmd The user input string representing the command.
     * @return A DeleteCommand object or null if the command does not match.
     */
    public Command parseIfDeleteCommand(String cmd) throws InvalidIndexCommandException {
        if (!cmd.startsWith("delete ")) {
            return null;
        }

        // Parse command to get the index of the task
        if (cmd.length() <= ("delete ").length()) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }

        try {
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexCommandException("Please enter a valid index.");
        }
    }

    /**
     * Parses the command if it is a find command.
     *
     * @param cmd The user input string representing the command.
     * @return A FindCommand object or null if the command does not match.
     */
    public Command parseIfFindCommand(String cmd) {
        if (!cmd.startsWith("find ")) {
            return null;
        }

        // Parse command to get the keyword
        String keyword = cmd.substring(("find ").length());

        return new FindCommand(keyword);
    }
}
