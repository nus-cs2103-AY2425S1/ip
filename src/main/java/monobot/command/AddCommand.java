package monobot.command;

import monobot.util.Storage;
import monobot.task.Task;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

/**
 * Represents add command to add a task to list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specific task type.
     *
     * @param task Task to be added to list.
     * @param type Specific type of task to be added to list.
     */
    public AddCommand(CommandType type, Task task) {
        super(type);
        this.task = task;
    }

    /**
     * Executes add command.
     *
     * @param tasks list to which task will be added to.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     * @throws MonoBotException IF unable to add task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.addTask(task);
        ui.printAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
