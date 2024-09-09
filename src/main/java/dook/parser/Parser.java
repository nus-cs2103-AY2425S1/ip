package dook.parser;

import dook.DookException;
import dook.commands.Command;
import dook.commands.CreateCommand;
import dook.commands.DeleteCommand;
import dook.commands.ExitCommand;
import dook.commands.FindCommand;
import dook.commands.ListCommand;
import dook.commands.MarkingCommands;
import dook.tasks.TaskType;

/**
 * The Parser class deals with making sense of the user's commands.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command object.
     *
     * @param input The raw input from the user.
     * @return The Command object corresponding to the user input.
     * @throws DookException If the input format is invalid or the command is unrecognised.
     */
    public Command parse(String input) throws DookException {
        try {
            String[] components = input.split(" ", 2);
            String command = components[0];

            switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkingCommands(Integer.parseInt(components[1]), true);
            case "unmark":
                return new MarkingCommands(Integer.parseInt(components[1]), false);
            case "todo":
                return new CreateCommand(components[1], TaskType.TODO);
            case "deadline":
                String[] deadlineComponents = components[1].split(" /by ", 2);
                return new CreateCommand(deadlineComponents[0], deadlineComponents[1].trim(), TaskType.DEADLINE);
            case "event":
                String[] eventComponents = components[1].split(" /from ", 2);
                String[] timings = eventComponents[1].split(" /to ", 2);
                return new CreateCommand(eventComponents[0], timings[0].trim(), timings[1].trim(), TaskType.EVENT);
            case "delete":
                return new DeleteCommand(Integer.parseInt(components[1]));
            case "find":
                return new FindCommand(components[1]);
            default:
                throw new DookException("Invalid command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DookException("Invalid format, could not parse");
        }
    }
}
