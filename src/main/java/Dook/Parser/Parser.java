package Dook.Parser;

import Dook.Commands.*;
import Dook.DookException;

public class Parser {
    public Command parse(String input) throws DookException {
        try {
            String[] components = input.split(" ", 2);
            String command = components[0];

            switch (command) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkingCommands(Integer.parseInt(components[1]), true);
                case "unmark":
                    return new MarkingCommands(Integer.parseInt(components[1]), false);
                case "todo":
                    return new CreateCommand(components[1], TaskType.TODO);
                case "deadline":
                    String[] deadlineComponents = components[1].split(" /by ", 2);
                    return new CreateCommand(deadlineComponents[0], deadlineComponents[1].trim(), TaskType.DEADLINE);
                case "event":
                    String[] eventComponents = components[1].split(" /from ", 2);
                    String[] timings = eventComponents[1].split(" /to ", 2);
                    return new CreateCommand(eventComponents[0], timings[0].trim(), timings[1].trim(), TaskType.EVENT);
                case "delete":
                    return new DeleteCommand(Integer.parseInt(components[1]));
                default:
                    throw new DookException("Invalid command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DookException("Invalid format, could not parse");
        }
    }
}
