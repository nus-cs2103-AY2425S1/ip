package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        Task task = tasks.markAsDone(index); // Adds the task to the task list.
        storage.writeToFile(tasks.getTasks()); // Updates storage with the new list of tasks.
        ui.printMarkTask(task); // Displays a confirmation message with the current task list
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
