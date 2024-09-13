package parser;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.RemoveCommand;

/**
 * Parses user input and generates appropriate Command objects.
 * The Parser class is responsible for interpreting user commands and returning
 * the corresponding Command objects that will be executed.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding Command object.
     * The method determines the type of command based on the first word of the input
     * and uses the remaining input as arguments for the command.
     *
     * @param parsed an array of strings containing the user's input, where the first element is the command
     * @return the Command object corresponding to the user's input
     */
    public static Command parse(String[] parsed) {
        return switch (parsed[0]) {
        case "bye" -> new ByeCommand();
        case "quit", "exit" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "remove" -> {
            if (parsed.length > 1) {
                yield new RemoveCommand(parsed[1]);
            }
            yield new InvalidCommand(parsed[0]);
        }
        case "add" -> {
            if (parsed.length > 1) {
                yield new AddCommand(parsed[1]);
            }
            yield new InvalidCommand(parsed[0]);
        }
        case "mark", "unmark" -> {
            if (parsed.length > 1) {
                yield new MarkCommand(parsed[0], parsed[1]);
            }
            yield new InvalidCommand(parsed[0]);
        }
        case "find" -> {
            if (parsed.length > 1 && !parsed[1].trim().isEmpty()) {
                yield new FindCommand(parsed[1]);
            }
            yield new InvalidCommand(parsed[0]);
        }
        case "help" -> new HelpCommand();
        default -> new InvalidCommand(parsed[0]);
        };
    }
}
