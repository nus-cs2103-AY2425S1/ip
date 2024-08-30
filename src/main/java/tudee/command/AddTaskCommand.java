package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command to add a task to the task list.
 * This command updates the task list, user interface, and storage.
 */
public class AddTaskCommand extends Command {
    private final Task task;

/**
 * Constructs an AddTaskCommand with the specified task.
 */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAdd(task, tasks.size());
        storage.save(tasks.get());
    }
}