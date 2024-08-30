package tudee.parser;

import tudee.command.Command;
import tudee.command.AddTaskCommand;
import tudee.command.ListCommand;
import tudee.command.ByeCommand;
import tudee.command.DeleteCommand;
import tudee.command.MarkCommand;
import tudee.command.UnmarkCommand;
import tudee.command.DateCommand;
import tudee.command.FindCommand;
import tudee.command.UnknownCommand;
import tudee.task.ToDo;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.TudeeException;

public class Parser {
    public static Command parse(String input) throws TudeeException {
        String[] inputList = input.split(" ", 2);
        String command = inputList[0];
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (command.equalsIgnoreCase("todo")) {
            return new AddTaskCommand(new ToDo(inputList[1]));
        } else if (command.equalsIgnoreCase("deadline")) {
            return new AddTaskCommand(new Deadline(inputList[1], inputList[2]));
        } else if (command.equalsIgnoreCase("event")) {
            return new AddTaskCommand(new Events(inputList[1], inputList[2], inputList[3]));
        } else if (command.equalsIgnoreCase("mark")) {
            return new MarkCommand(Integer.parseInt(inputList[1]));
        } else if (command.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(Integer.parseInt(inputList[1]));
        } else if (command.equalsIgnoreCase("delete")) {
            return new DeleteCommand(Integer.parseInt(inputList[1]));
        } else if (command.equalsIgnoreCase("date")) {
            return new DateCommand(inputList[1]);
        } else if (command.equalsIgnoreCase("find")) {
            return new FindCommand(inputList[1]);
        }
        else {
            return new UnknownCommand();
        }
    }
}
