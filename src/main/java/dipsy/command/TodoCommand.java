package dipsy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.task.ToDo;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 * The command follows the format "todo &lt;description&gt;".
 */
public class TodoCommand extends Command {

    /** Regular expression pattern to parse the todo command input. */
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");

    /**
     * Constructs a {@code TodoCommand} with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public TodoCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the {@code TodoCommand} by parsing the user input and adding a new ToDo task to the task list.
     *
     * @return A message indicating that the {@code Todo} task has been successfully added, including
     *      the task details and the updated number of tasks in the task list.
     * @throws InvalidCommandException If the command format is invalid.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Matcher matcher = TODO_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            return ui.getTaskAddedMessage(todo, tasks.getSize());
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_TODO);
        }
    }
}
