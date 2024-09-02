package parser;

import commands.*;

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
            case "bye" -> new byeCommand();
            case "quit", "exit" -> new exitCommand();
            case "list" -> new listCommand();
            case "remove" -> {
                if (parsed.length > 1) {
                    yield new removeCommand(parsed[1]);
                }
                yield new invalidCommand(parsed[0]);
            }
            case "add" -> {
                if (parsed.length > 1) {
                    yield new addCommand(parsed[1]);
                }
                yield new invalidCommand(parsed[0]);
            }
            case "mark", "unmark" -> {
                if (parsed.length > 1) {
                    yield new markCommand(parsed[0], parsed[1]);
                }
                yield new invalidCommand(parsed[0]);
            }
            case "help" -> new helpCommand();
            default -> new invalidCommand(parsed[0]);
        };
    }
}
