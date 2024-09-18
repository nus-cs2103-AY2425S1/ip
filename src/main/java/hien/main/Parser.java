package hien.main;

import hien.command.Command;
import hien.command.DeadlineCommand;
import hien.command.DeleteCommand;
import hien.command.EventCommand;
import hien.command.ExitCommand;
import hien.command.FindCommand;
import hien.command.ListCommand;
import hien.command.MarkCommand;
import hien.command.TodoCommand;
import hien.exception.HienException;
import hien.ui.UI;


class Parser {
    private UI ui;

    /**
     * Parses the user's input string and returns the corresponding command.
     * The method processes various types of commands
     * (e.g., list, mark, unmark, todo, deadline, event, delete, deleteall, find, bye)
     * based on the first word of the input string. If the command type is invalid, an exception is thrown.
     *
     * @param input The input string provided by the user. It consists of a command followed by arguments.
     * @return A {@code Command} object representing the action to be performed based on the user's input.
     * @throws HienException If the command type is not recognized or invalid.
     */
    public static Command parse(String input) throws HienException {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        switch (commandType) {
        case "list":
            return new ListCommand(false);
        case "mark":
            return new MarkCommand(input, true, false);
        case "unmark":
            return new MarkCommand(input, false, false);
        case "todo":
            return new TodoCommand(input, false);
        case "deadline":
            // Parse deadline details and create Deadline object
            return new DeadlineCommand(input, false);
        case "event":
            // Parse event details and create Event object
            return new EventCommand(input, false);
        case "delete":
            return new DeleteCommand(input, false, false);
        case "deleteall":
            return new DeleteCommand(input, false, true);
        case "find":
            String keyword = parts.length > 1 ? parts[1] : "";
            if (keyword.isEmpty()) {
                throw new HienException("☹ OOPS!!! The find command needs a keyword. Usage: find <keyword>");
            }
            return new FindCommand(keyword, false);
        case "bye":
            return new ExitCommand(true);
        default:
            throw new HienException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}