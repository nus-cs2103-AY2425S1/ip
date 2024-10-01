package alisa;

import alisa.command.Command;
import alisa.command.EditCommand;
import alisa.command.MarkCommand;
import alisa.command.UnmarkCommand;
import alisa.command.AddCommand;
import alisa.command.DeleteCommand;
import alisa.command.ExitCommand;
import alisa.command.ListCommand;
import alisa.command.FindCommand;
import alisa.exception.AlisaException;
import alisa.task.Deadline;
import alisa.task.Event;
import alisa.task.Todo;

public class Parser {

    /**
     * Parses the input and returns a command based on the input.
     *
     * @param command Input given by user.
     * @return Command to execute based on the input.
     * @throws AlisaException If the input gives an unknown command.
     */
    public static Command parse(String command) throws AlisaException {
        String[] commandArray = command.split(" ", 2);
        switch (commandArray[0]) {
            case "mark":
                return new MarkCommand(Integer.parseInt(commandArray[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(commandArray[1]));
            case "todo":
                return new AddCommand(new Todo(commandArray[1]));
            case "deadline":
                String[] deadlineDescription = commandArray[1].split(" /by ");
                return new AddCommand(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            case "event":
                String[] eventDescription = commandArray[1].split(" /from | /to ");
                return new AddCommand(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(commandArray[1]));
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "find":
                return new FindCommand(commandArray[1]);
            case "edit":
                //edit [1 description "hello there"]
                String[] editDescription = commandArray[1].split(" ", 3);
                String editedContent = editDescription[2].substring(1, editDescription[2].length() - 1);
                return new EditCommand(Integer.parseInt(editDescription[0]), editDescription[1], editedContent);
            default:
                throw new AlisaException("Sorry, I didn't quite catch that. Put in a command that I understand");
        }
    }
}
