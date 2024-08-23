package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list in the DailyTasks application.
 * This command deletes the task at the specified index and displays a message confirming the deletion.
 */
public class DeleteTaskCommand extends Command {

    /** The index of the task to be deleted. */
    private final int taskIndex;

    /**
     * Constructs a new DeleteTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteTaskCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by deleting a task from the task list.
     * If the task index is valid, the task is removed and a message confirming the deletion is displayed.
     *
     * @param taskList The list of tasks from which the task will be deleted.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system responsible for saving and loading tasks (not used in this implementation).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskIndex != -1) {
            Task task = taskList.deleteTask(this.taskIndex);
            System.out.println(Ui.formatDeleteTask(task, taskList.getTasks().size()));
        }
    }
}
