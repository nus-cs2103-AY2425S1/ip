package commands;

import storage.Storage;
import tasks.Task;
import taskList.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Execution actions:
     * - Add task to the task list
     * - Write updated task list to storage
     * - Print new task and inform user on total tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.writeToTextStorage(tasks);
        ui.printGenericMessage( "Got it. I've added this task:\n  " +
                task + "\nNow you have " + tasks.length() + " tasks in the list.");
    }
}
