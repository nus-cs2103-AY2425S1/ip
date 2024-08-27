package com.Nimbus;

final public class Parser {
    public enum Command {
        Remove,
        List,
        Mark,
        Unmark,
        Todo,
        Deadline,
        Event,
        Bye
    }

    public static Command getCommandType(String line) throws InvalidCommandException {
        int index = line.indexOf(" ");
        String command = line;
        if (index != -1) {
            command = line.substring(0, index);
        }
        return switch (command) {
            case "list" -> Command.List;
            case "bye" -> Command.Bye;
            case "remove" -> Command.Remove;
            case "mark" -> Command.Mark;
            case "unmark" -> Command.Unmark;
            case "todo" -> Command.Todo;
            case "deadline" -> Command.Deadline;
            case "event" -> Command.Event;
            default -> throw new InvalidCommandException(command);
        };
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

    public Task parse(String line) {

    }
}
