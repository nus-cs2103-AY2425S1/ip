package skywalker.command;

import java.io.IOException;

import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;


/**
 * customise a special kind of command with add functionality.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command
     * displays the task saved and saves the updated task.
     *
     * @param tasks The task list which would be added to
     * @param ui The UI object is used to show message to user
     * @param storage The place where task list are stored
     * @throws IOException IOException happens if an I/O error occurs
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (tasks.isDuplicate(task)) {
            return "Duplicate task: " + task.getDescription() + " already exists!";
        } else {
            tasks.addTask(task);
            storage.save(tasks);
            return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
        }
    }

}
