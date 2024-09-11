package command;

import assertions.AssertCommand;
import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds the task to the task list and updates the storage file.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        new AssertCommand(task, tasks, ui, storage).assertExecute(task, tasks, ui, storage);
        tasks.add(task);
        String stringOfTask = TaskList.arrayToNumberedString(tasks);
        storage.write(stringOfTask);
        ui.beautifyMessage("Got it. I've added this task:\n" +
                task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        return ui.beautifyMessage(stringOfTask);
    }
}
