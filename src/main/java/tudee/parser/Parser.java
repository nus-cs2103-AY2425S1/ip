package tudee.parser;

import tudee.TudeeException;
import tudee.command.AddTaskCommand;
import tudee.command.ByeCommand;
import tudee.command.Command;
import tudee.command.DateCommand;
import tudee.command.DeleteCommand;
import tudee.command.FindCommand;
import tudee.command.ListCommand;
import tudee.command.MarkCommand;
import tudee.command.UnknownCommand;
import tudee.command.UnmarkCommand;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.task.ToDo;

/**
 * Parses user input into respective Command objects.
 * This class interprets the user's input and returns the appropriate Command based on the input string.
 */
public class Parser {

    /**
     * Parses the given input string to generate the respective Command.
     * The input string is expected to contain a command followed by optional arguments depending on the command called.
     * The method recognises commands such as "list", "bye", "todo", etc.
     * Each command is mapped to a specific Command subclass.
     *
     * @param input the user input string to be parsed.
     * @return the corresponding Command based on the input.
     * @throws TudeeException if the input is invalid or not recognized.
     */
    public static Command parse(String input) throws TudeeException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];

        // Assert that the command part is not empty.
        assert command != null && !command.isEmpty() : "Input should not be empty";
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (command.equalsIgnoreCase("todo")) {
            // Assert that input contains a task description for the ToDo task.
            assert inputs.length > 1 : "Specify the task description!";
            return new AddTaskCommand(new ToDo(inputs[1]));
        } else if (command.equalsIgnoreCase("deadline")) {
            String[] details = inputs[1].split("/by ");
            // Assert that the task description and the deadline are provided.
            assert details.length == 2 : "Specify the task description or deadline (/by)!";
            return new AddTaskCommand(new Deadline(details[0], details[1]));
        } else if (command.equalsIgnoreCase("event")) {
            // Assert that the task description, start and end date are provided.
            String[] details = inputs[1].split("/from | /to ");
            assert details.length == 3 : "Specify the task description, start (/from) and end date (/to)!";
            return new AddTaskCommand(new Events(details[0], details[1], details[2]));
        } else if (command.equalsIgnoreCase("mark")) {
            // Assert that a task number is provided to be marked.
            assert inputs.length > 1 : "Specify the index of the task to be marked!";
            return new MarkCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("unmark")) {
            // Assert that a task number is provided to be unmarked.
            assert inputs.length > 1 : "Specify the index of the task to be unmarked!";
            return new UnmarkCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("delete")) {
            // Assert that a task number is provided to be deleted.
            assert inputs.length > 1 : "Specify the index of the task to be deleted!";
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        } else if (command.equalsIgnoreCase("date")) {
            // Assert that a date is provided.
            assert inputs.length > 1 : "Specify the date you wish to check!";
            return new DateCommand(inputs[1]);
        } else if (command.equalsIgnoreCase("find")) {
            // Assert that a keyword is provided to find tasks with that keyword.
            assert inputs.length > 1 : "Specify the keyword you wish to find!";
            return new FindCommand(inputs[1]);
        } else {
            return new UnknownCommand();
        }
    }
}
