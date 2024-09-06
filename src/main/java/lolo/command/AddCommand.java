package lolo.command;

import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 * This command interacts with the user interface to confirm the addition
 * and updates the storage with the new task list.
 */
class AddCommand extends Command {
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
     * Executes the command by adding the task to the task list,
     * displaying a confirmation message to the user, and saving
     * the updated task list to storage.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param storage The storage to save the updated task list.
     * @return A string message confirming the addition of the task.
     * @throws LoloException If there is an error during execution or saving.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws LoloException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }
}

