package tudee.parser;

import tudee.task.ToDo;
import tudee.task.Deadline;
import tudee.task.Events;
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
import tudee.TudeeException;

/**
 * Parses user input into respective Command objects.
 * This class interprets the user's input and returns the appropriate Command
 * based on the input string.
 */
public class Parser {

    /**
     * Parses the given input string to generate the respective Command.
     * The input string is expected to contain a command followed by
     * optional arguments depending on the command called. The method
     * recognises commands such as "list", "bye", "todo", "deadline",
     * "event", "mark", "unmark", "delete", and "date". Each command
     * is mapped to a specific Command subclass.
     *
     * @param input the user input string to be parsed
     * @return the corresponding Command based on the input
     * @throws TudeeException if the input is invalid or not recognized
     */
    public static Command parse(String input) throws TudeeException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (command.equalsIgnoreCase("todo")) {
            return new AddTaskCommand(new ToDo(inputs[1]));
        } else if (command.equalsIgnoreCase("deadline")) {
            return new AddTaskCommand(new Deadline(inputs[1], inputs[2]));
        } else if (command.equalsIgnoreCase("event")) {
            return new AddTaskCommand(new Events(inputs[1], inputs[2], inputs[3]));
        } else if (command.equalsIgnoreCase("mark")) {
            return new MarkCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("delete")) {
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("date")) {
            return new DateCommand(inputs[1]);
        } else if (command.equalsIgnoreCase("find")) {
            return new FindCommand(inputs[1]);
        } else {
            return new UnknownCommand();
        }
    }
}
