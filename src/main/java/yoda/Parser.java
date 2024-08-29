package yoda;

import yoda.commands.ByeCommand;
import yoda.commands.ListCommand;
import yoda.commands.MarkCommand;
import yoda.commands.UnmarkCommand;
import yoda.commands.DeleteCommand;
import yoda.commands.TodoCommand;
import yoda.commands.DeadlineCommand;
import yoda.commands.EventCommand;
import yoda.commands.Command;
import yoda.exceptions.EmptyInputException;
import yoda.exceptions.InvalidInputException;
import yoda.exceptions.NonsenseInputException;
import java.util.Scanner;

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
        default:
            throw new NonsenseInputException();
        }
        return command;
    }
}
