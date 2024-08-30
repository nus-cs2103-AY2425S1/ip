package parser;

import command.*;
import exceptions.BuddyException;

public class Parser {
    public static Command parse(String command) throws BuddyException {
        if (command.startsWith("todo")) {
            return new AddCommand(command);
        } else if (command.startsWith("deadline")) {
            return new AddCommand(command);
        } else if (command.startsWith("event")) {
            return new AddCommand(command);
        } else if (command.startsWith("delete")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            return new DeleteCommand(taskIndex);
        } else if (command.startsWith("mark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            return new MarkCommand(taskIndex);
        } else if (command.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            return new UnmarkCommand(taskIndex);
        } else if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else {
            throw new BuddyException("Unknown command?");
        }
    }

}
