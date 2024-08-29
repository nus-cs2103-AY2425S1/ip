package Victor.Parser;

import Victor.Commands.Command;
import Victor.Commands.DeadlineCommand;
import Victor.Commands.DeleteCommand;
import Victor.Commands.EventCommand;
import Victor.Commands.ExitCommand;
import Victor.Commands.ListCommand;
import Victor.Commands.MarkCommand;
import Victor.Commands.ToDoCommand;
import Victor.Commands.UnmarkCommand;

public class Parser {
    public Command parseInput(String input) {
        Command command;
        String[] inputWords = input.trim().split(" ");
        command = switch (inputWords[0]) {
            case "todo" -> new ToDoCommand(inputWords);
            case "deadline" -> new DeadlineCommand(inputWords);
            case "event" -> new EventCommand(inputWords);
            case "delete" -> new DeleteCommand(inputWords);
            case "mark" -> new MarkCommand(inputWords);
            case "unmark" -> new UnmarkCommand(inputWords);
            case "list" -> new ListCommand(inputWords);
            case "bye" -> new ExitCommand(inputWords);
            default -> new Command(inputWords);
        };
        return command;
    }
}