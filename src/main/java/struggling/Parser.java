package struggling;

import java.time.LocalDate;

import struggling.command.AddCommand;
import struggling.command.Command;
import struggling.command.DeleteCommand;
import struggling.command.ExitCommand;
import struggling.command.FindCommand;
import struggling.command.HighPriorityCommand;
import struggling.command.InvalidCommand;
import struggling.command.ListCommand;
import struggling.command.LowPriorityCommand;
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

    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;

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
            return new AddCommand(createTodo(cmd));
        case deadline:
            return new AddCommand(createDeadline(cmd));
        case event:
            return new AddCommand(createEvent(cmd));
        case delete:
            int deleteIndex = Integer.parseInt(args[1]) - 1;
            assert deleteIndex >= 0 : "index should be >= 0";
            return new DeleteCommand(deleteIndex);
        case find:
            return new FindCommand(args[1]);
        case highPriority:
            int highPriorityIndex = Integer.parseInt(args[1]) - 1;
            assert highPriorityIndex >= 0 : "index should be >= 0";
            return new HighPriorityCommand(highPriorityIndex);
        case lowPriority:
            int lowPriorityIndex = Integer.parseInt(args[1]) - 1;
            assert lowPriorityIndex >= 0 : "index should be >= 0";
            return new LowPriorityCommand(lowPriorityIndex);
        default:
            return new InvalidCommand();
        }
    }

    private static ToDo createTodo(String cmd) {
        try {
            String description = cmd.substring(TODO_DESCRIPTION_INDEX);
            return new ToDo(description);
        } catch (StringIndexOutOfBoundsException e) {
            throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static Task createDeadline(String cmd) {
        final String byKeyword = "/by ";
        int byIndex = cmd.indexOf(byKeyword) + byKeyword.length();

        String description = cmd
                .substring(DEADLINE_DESCRIPTION_INDEX, cmd.indexOf(byKeyword))
                .trim();
        LocalDate by = LocalDate.parse(cmd.substring(byIndex));

        return new Deadline(description, by);
    }

    private static Task createEvent(String cmd) {
        final String fromKeyword = "/from ";
        final String toKeyword = "/to ";
        int fromIndex = cmd.indexOf(fromKeyword) + fromKeyword.length();
        int toIndex = cmd.indexOf(toKeyword) + toKeyword.length();

        assert fromIndex >= 0 : "index should be >= 0";
        assert toIndex >= 0 : "index should be >= 0";

        String description = cmd
                .substring(EVENT_DESCRIPTION_INDEX, cmd.indexOf(fromKeyword))
                .trim();
        String from = cmd
                .substring(fromIndex, cmd.indexOf(toKeyword))
                .trim();
        String to = cmd.substring(toIndex);

        return new Event(description, from, to);
    }

    /**
     * Commands accepted by the Parser class.
     */
    private enum Commands {
        bye, list, mark, unmark, todo, deadline, event, delete, find, highPriority, lowPriority
    }


}
