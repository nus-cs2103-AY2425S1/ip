package com.nimbus;

import com.commands.*;

final public class Parser {

    public static String getCommand(String line) {
        int index = line.indexOf(" ");
        String command = line;
        if (index != -1) {
            command = line.substring(0, index);
        }
        return command;
    }

    public static String getDescription(String argument) {
        int index = argument.indexOf("/");
        if (index == -1)
            return argument.trim();
        else
            return argument.substring(0, index).trim();
    }

    public static String getArgument(String command) throws InvalidArgumentException {
        command = command.trim();
        int index = command.indexOf(" ");
        if (index == -1)
            throw new InvalidArgumentException("Error: Empty Argument!");
        return command.substring(index + 1);
    }

    public static String readOption(String argument, String target) throws InvalidArgumentException {
        int startIndex = argument.indexOf("/" + target);
        if (startIndex == -1) {
            throw new InvalidArgumentException("Error: Missing option: " + target);
        }
        String substringAfterOption = argument.substring(startIndex + target.length() + 1);
        int endIndex = substringAfterOption.indexOf("/");
        if (endIndex == -1)
            return substringAfterOption.trim();
        else
            return argument.substring(startIndex + target.length() + 1,
                    endIndex + startIndex + target.length() + 1).trim();
    }

    public static Command parse(String line) throws InvalidCommandException, InvalidArgumentException {
        return switch (getCommand(line)) {
            case "list" -> new ListCommand();
            case "remove" -> new RemoveCommand(getArgument(line));
            case "mark" -> new MarkCommand(getArgument(line));
            case "unmark" -> new UnmarkCommand(getArgument(line));
            case "todo" -> new TodoCommand(getArgument(line));
            case "deadline" -> new DeadlineCommand(getArgument(line));
            case "event" -> new EventCommand(getArgument(line));
            case "bye" -> new ByeCommand();
            case "find" -> new FindCommand(getArgument(line));
            default -> throw new InvalidCommandException(getCommand(line));
        };
    }
}
