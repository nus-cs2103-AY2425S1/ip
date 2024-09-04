package krona.command;

import krona.exception.KronaException;
import krona.storage.Storage;
import krona.task.Task;
import krona.task.TaskList;
import krona.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list, showing relevant messages,
     * and saving the updated task list to storage.
     *
     * @param tasks The task list that the command operates on.
     * @param ui The UI component that handles interactions with the user.
     * @param storage The storage component that handles saving the updated task list.
     * @throws KronaException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        tasks.addTask(task);
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(task.toString()).append("\n");
        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        storage.save(tasks);

        ui.setCombinedMessage(output.toString());
    }
}
