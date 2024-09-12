package sentinel.command;

import sentinel.Sentinel;
import sentinel.exception.ExecutionException;
import sentinel.exception.SentinelException;
import sentinel.task.Task;
import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;
import sentinel.utils.SentinelString;

/**
 * The CreateTaskCommand class is responsible for creating a new task based on the user's input.
 * This command can create tasks of type ToDo, Deadline, or Event, depending on the specified command type.
 */
public class CreateTaskCommand extends Command {
    private final Sentinel.CommandType commandType;

    /**
     * Constructs a CreateTaskCommand with the specified user interface, task list, and command type.
     *
     * @param ui          The user interface object for displaying messages.
     * @param list        The list of tasks managed by the application.
     * @param commandType The type of task to create (ToDo, Deadline, or Event).
     */
    public CreateTaskCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    /**
     * Executes the task creation command with the given input.
     * Parses the input to create a new task and adds it to the task list if the parsing is successful.
     * Displays a message confirming the addition of the task.
     *
     * @param input The user's input string.
     * @throws SentinelException If an error occurs during task parsing.
     */
    @Override
    public String execute(String input) throws SentinelException {
        Task newTask = Parser.parseTask(commandType, input, ui);
        if (newTask == null) {
            throw new ExecutionException("Unable to execute CreateTask");
        }
        list.add(newTask);
        ui.showAddedTask(newTask);
        return SentinelString.stringAddedTask(newTask);
    }
}
