package sage.parser;

import sage.command.*;
import sage.exception.SageException;

/**
 * Represents the parser that interprets user input and converts it into commands
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command
     *
     * @param input The raw input string entered by the user
     * @return The Command object representing the user intended action
     * @throws SageException If the input command is not recognised or if the required arguments are invalid
     */
    public Command parse(String input) throws SageException {
        String parts[] = input.split(" ", 2);
        String userCommand = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (userCommand) {
            case "list":
                // Return a ListCommand to display the list of tasks
                return new ListCommand();
            case "mark":
                // Return a MarkCommand to mark a task as done, using the provided task index.
                return new MarkCommand(Integer.parseInt(arguments));
            case "unmark":
                // Return an UnmarkCommand to unmark a task, using the provided task index.
                return new UnmarkCommand(Integer.parseInt(arguments));
            case "delete":
                // Return a DeleteCommand to delete a task, using the provided task index.
                return new DeleteCommand(Integer.parseInt(arguments));
            case "todo":
                // Return a ToDoCommand to add a new todo task with the given description.
                return new ToDoCommand(arguments);
            case "deadline":
                // Return a DeadlineCommand to add a new deadline task with the given details.
                return new DeadlineCommand(arguments);
            case "event":
                // Return an EventCommand to add a new event task with the given details.
                return new EventCommand(arguments);
            case "find":
                // Return a FindCommand to find tasks with the keyword
                return new FindCommand(arguments);
            case "bye":
                // Return an ExitCommand to terminate the application.
                return new ExitCommand();
            default:
                // Throw an exception if the command is not recognized.
                throw new SageException("Sorry, what do you mean? :p");
        }
    }
}
