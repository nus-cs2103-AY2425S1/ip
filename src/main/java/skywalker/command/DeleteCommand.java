package skywalker.command;

import java.io.IOException;

import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;


/**
 * Customise a delete command
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, which removes the task from the task list,
     * displays a confirmation to the user, and saves the updated task list to storage.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The UI object used to display messages to the user.
     * @param storage The storage object used to save the task list.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        storage.save(tasks);

        StringBuilder result = new StringBuilder();
        result.append("Noted. I've removed this task:\n");
        result.append(task).append("\n");
        result.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return result.toString();
    }

}
