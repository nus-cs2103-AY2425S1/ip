package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Represents a "Delete task" command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Gets the task to be deleted from the task list and delete it.
     * Also prints out a statement to inform the user.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format or if the task number does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from taskList
        Task targetTask = tasks.getTask(this.getInput(), "delete ");

        // Delete the task
        tasks.remove(targetTask);

        // Update the storage
        storage.writeTaskList(tasks);

        ui.printText("Noted. I've removed this task:\n   " + targetTask.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }
}
