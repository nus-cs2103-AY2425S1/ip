package MichaelScott.Parser;

import MichaelScott.Command.*;
import MichaelScott.Exception.MichaelScottException;

/**
 * Class used to parse the text input received from Standard input
 */
public class Parser {

    /**
     * Parses a command string and returns the corresponding Command object.
     *
     * @param fullCommand The command string to be parsed.
     * @return A Command object that corresponds to the parsed command.
     * @throws MichaelScottException If the command is not recognized or is invalid.
     */
    public Command parse(String fullCommand) throws MichaelScottException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

//        MichaelScott.task.Todo think enums
        return switch (command.toLowerCase()) {
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "delete" -> new DeleteCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "clear" -> new ClearlistCommand();
            default -> throw new MichaelScottException("I don't understand what you mean to say!");
        };
    }
}
