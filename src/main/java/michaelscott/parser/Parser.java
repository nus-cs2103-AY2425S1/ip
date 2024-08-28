package michaelscott.parser;

import michaelscott.command.ClearlistCommand;
import michaelscott.command.Command;
import michaelscott.command.DeadlineCommand;
import michaelscott.command.DeleteCommand;
import michaelscott.command.EventCommand;
import michaelscott.command.ExitCommand;
import michaelscott.command.ListCommand;
import michaelscott.command.MarkCommand;
import michaelscott.command.TodoCommand;
import michaelscott.command.UnmarkCommand;
import michaelscott.exception.MichaelScottException;

public class Parser {
    public Command parse(String fullCommand) throws MichaelScottException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        //michaelscott.MichaelScott.task.Todo think enums
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
