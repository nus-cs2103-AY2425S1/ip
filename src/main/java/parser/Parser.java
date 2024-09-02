package parser;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.RemoveCommand;

public class Parser {

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
        case "help" -> new HelpCommand();
        default -> new InvalidCommand(parsed[0]);
        };
    }
}
