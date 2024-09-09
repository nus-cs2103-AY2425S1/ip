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
            int markIndex = Integer.parseInt(args[1]) - 1;
            assert markIndex >= 0 : "index should be >= 0";
            return new MarkCommand(markIndex);
        case unmark:
            int unmarkIndex = Integer.parseInt(args[1]) - 1;
            assert unmarkIndex >= 0 : "index should be >= 0";
            return new UnmarkCommand(unmarkIndex);
        case todo:
            try {
                Task todo = new ToDo(cmd.substring(5));
                return new AddCommand(todo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
            }
        case deadline:
            int byIndex = cmd.indexOf("/by ");

            String dDescription = cmd.substring(9, byIndex).trim();
            LocalDate dBy = LocalDate.parse(cmd.substring(byIndex + 4));

            Task deadline = new Deadline(dDescription, dBy);
            return new AddCommand(deadline);
        case event:
            int fromIndex = cmd.indexOf("/from ");
            int toIndex = cmd.indexOf("/to ");

            assert fromIndex >= 0 : "index should be >= 0";
            assert toIndex >= 0 : "index should be >= 0";

            String eDescription = cmd.substring(6, fromIndex).trim();
            String eFrom = cmd.substring(fromIndex + 6, toIndex).trim();
            String eTo = cmd.substring(toIndex + 4);

            Task event = new Event(eDescription, eFrom, eTo);
            return new AddCommand(event);
        case delete:
            int deleteIndex = Integer.parseInt(args[1]) - 1;
            assert deleteIndex >= 0 : "index should be >= 0";
            return new DeleteCommand(deleteIndex);
        case find:
            return new FindCommand(args[1]);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Commands accepted by the Parser class.
     */
    private enum Commands {
        bye, list, mark, unmark, todo, deadline, event, delete, find
    }


}
