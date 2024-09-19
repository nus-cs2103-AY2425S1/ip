package commands;

import exceptions.GrokInvalidUserInputException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Adds some task into the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Adds a provided task to the task list
     * @param task the task to add to the task list
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Execution actions:
     * - Ensure that task description is unique before adding
     * - Add task to the task list
     * - Write updated task list to storage
     * - Print new task and inform user on total tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isDuplicate = tasks.checkIfDuplicate(task);

        if (isDuplicate) {
            return "Duplicate task description detected - task not saved.";
        }

        tasks.addTask(task);
        storage.writeToTextStorage(tasks);
        return "Got it. I've added this task:\n  "
                + task
                + "\nNow you have " + tasks.length() + " tasks in the list.";
    }
}
