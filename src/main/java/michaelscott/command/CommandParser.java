package michaelscott.command;

import michaelscott.utils.MichaelScottException;

public class CommandParser {
    public Command parse(String fullCommand) throws MichaelScottException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

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
            case "find" -> new FindCommand(args);
            default -> throw new MichaelScottException("I don't understand what you mean to say!");
        };
    }
}
