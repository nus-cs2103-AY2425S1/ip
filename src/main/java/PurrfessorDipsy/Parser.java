import java.util.regex.Pattern;
import Exception.UnknownCommandException;
import Command.Command;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.TodoCommand;
import Command.DeadlineCommand;
import Command.EventCommand;
import Command.DeleteCommand;
import Command.ListCommand;
import Command.ByeCommand;

public class Parser {
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d+)");

    public static Command parseCommand(String userInput) throws UnknownCommandException {
        if (userInput.startsWith("mark")) return new MarkCommand();
        if (userInput.startsWith("unmark")) return new UnmarkCommand();
        if (userInput.startsWith("todo")) return new TodoCommand();
        if (userInput.startsWith("deadline")) return new DeadlineCommand();
        if (userInput.startsWith("event")) return new EventCommand();
        if (userInput.startsWith("delete")) return new DeleteCommand();
        if (userInput.startsWith("list")) return new ListCommand();
        if (userInput.equals("bye")) return new ByeCommand();
        throw new UnknownCommandException();
    }

}
