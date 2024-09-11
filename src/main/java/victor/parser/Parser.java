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

/**
 * Parser class that handles string input from user and determined command
 * object to return based on first keyword.
 */
public class Parser {
    /**
     * Parses user input string and classifies input by type of command done. Parsing depends
     * on first word of user input. If no word is matched, a blank command is returned.
     * @param input The string of user input from the input stream.
     * @return Command with details of user input.
     */
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
        // command should not be null after switch condition
        assert(command != null);
        return command;
    }
}
