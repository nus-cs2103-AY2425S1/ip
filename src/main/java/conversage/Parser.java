package conversage;

import conversage.command.*;
import conversage.exception.ConverSageException;
import conversage.task.Deadline;
import conversage.task.Event;
import conversage.task.ToDo;

public class Parser {
    public static Command parse(String fullCommand) throws ConverSageException {
        String[] commandParts = fullCommand.split(" ", 2);
        String command = commandParts[0];

        switch (command.toLowerCase()) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "mark":
            return new MarkCommand(commandParts[1]);
        case "unmark":
            return new UnmarkCommand(commandParts[1]);
        case "todo":
            return new AddCommand(new ToDo(commandParts[1]));
        case "deadline":
            return new AddCommand(Parser.parseDeadline(commandParts[1]));
        case "event":
            return new AddCommand(Parser.parseEvent(commandParts[1]));
        case "delete":
            return new DeleteCommand(commandParts[1]);
        default:
            throw new ConverSageException("Invalid command, please try again");
        }
    }

    private static Deadline parseDeadline(String input) throws ConverSageException {
        if (!input.contains(" /by ")) {
            throw new ConverSageException("The deadline task requires a description and a deadline.");
        }
        String[] parts = input.split(" /by ");
        return new Deadline(parts[0], parts[1]);
    }

    private static Event parseEvent(String input) throws ConverSageException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ConverSageException("The event task requires a start time and an end time");
        }
        String[] parts = input.split(" /from | /to ");
        return new Event(parts[0], parts[1], parts[2]);
    }
}
