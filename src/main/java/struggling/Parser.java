package struggling;

import java.time.LocalDate;

import struggling.command.AddCommand;
import struggling.command.Command;
import struggling.command.DeleteCommand;
import struggling.command.ExitCommand;
import struggling.command.FindCommand;
import struggling.command.InvalidCommand;
import struggling.command.ListCommand;
import struggling.command.MarkCommand;
import struggling.command.UnmarkCommand;
import struggling.task.Deadline;
import struggling.task.Event;
import struggling.task.Task;
import struggling.task.ToDo;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the full command and creates Command
     * object with arguments.
     *
     * @param cmd Full command entered by the user.
     * @return A Command object to be executed.
     * @throws StrugglingException If there are missing arguments in the full command.
     */
    public static Command parse(String cmd) {
        String[] args = cmd.split(" ");

        switch (Commands.valueOf(args[0])) {
        case bye:
            return new ExitCommand();
        case list:
            return new ListCommand();
        case mark:
            return new MarkCommand(Integer.parseInt(args[1]) - 1);
        case unmark:
            return new UnmarkCommand(Integer.parseInt(args[1]) - 1);
        case todo:
            return new AddCommand(createTodo(cmd));
        case deadline:
            return new AddCommand(createDeadline(cmd));
        case event:
            return new AddCommand(createEvent(cmd));
        case delete:
            return new DeleteCommand(Integer.parseInt(args[1]) - 1);
        case find:
            return new FindCommand(args[1]);
        default:
            return new InvalidCommand();
        }
    }

    private static ToDo createTodo(String cmd) {
        try {
            String description = cmd.substring(5);
            return new ToDo(description);
        } catch (StringIndexOutOfBoundsException e) {
            throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static Task createDeadline(String cmd) {
        int byIndex = cmd.indexOf("/by ");

        String description = cmd.substring(9, byIndex).trim();
        LocalDate by = LocalDate.parse(cmd.substring(byIndex + 4));

        return new Deadline(description, by);
    }

    private static Task createEvent(String cmd) {
        int fromIndex = cmd.indexOf("/from ");
        int toIndex = cmd.indexOf("/to ");

        String description = cmd.substring(6, fromIndex).trim();
        String from = cmd.substring(fromIndex + 6, toIndex).trim();
        String to = cmd.substring(toIndex + 4);

        return new Event(description, from, to);
    }

    /**
     * Commands accepted by the Parser class.
     */
    private enum Commands {
        bye, list, mark, unmark, todo, deadline, event, delete, find
    }


}
