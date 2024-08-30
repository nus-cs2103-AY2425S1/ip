package yoda;

import yoda.commands.*;
import yoda.exceptions.EmptyInputException;
import yoda.exceptions.InvalidInputException;
import yoda.exceptions.NonsenseInputException;

public class Parser {

    public Command handle(String input, TaskList tasks) throws InvalidInputException {
        String[] splitInput = input.split(" ", 2);
        String commandString = splitInput[0];
        Command command = null;

        switch (commandString) {
        case "":
            throw new EmptyInputException();
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand(tasks);
            break;
        case "mark":
            command = new MarkCommand(tasks, input);
            break;
        case "unmark":
            command = new UnmarkCommand(tasks, input);
            break;
        case "delete":
            command = new DeleteCommand(tasks, input);
            break;
        case "todo":
            command = new TodoCommand(tasks, input);
            break;
        case "deadline":
            command = new DeadlineCommand(tasks, input);
            break;
        case "event":
            command = new EventCommand(tasks, input);
            break;
        case "find":
            command = new FindCommand(tasks, input);
            break;
        default:
            throw new NonsenseInputException();
        }
        return command;
    }
}
