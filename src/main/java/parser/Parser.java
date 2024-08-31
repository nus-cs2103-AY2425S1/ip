package parser;

import commands.*;

public class Parser {

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
