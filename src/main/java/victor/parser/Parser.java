package victor.parser;

import victor.commands.Command;
import victor.commands.DeadlineCommand;
import victor.commands.DeleteCommand;
import victor.commands.EventCommand;
import victor.commands.ExitCommand;
import victor.commands.FindCommand;
import victor.commands.ListCommand;
import victor.commands.MarkCommand;
import victor.commands.ToDoCommand;
import victor.commands.UnmarkCommand;

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
        case "find" -> new FindCommand(inputWords);
        default -> new Command(inputWords);
        };
        return command;
    }
}
