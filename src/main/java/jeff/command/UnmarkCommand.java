package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Represents a "Mark task as not yet done" command.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor for UnmarkCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Gets the target task from the task list and mark it as not done yet.
     * Also prints out a statement to inform the user.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format or if the task has not been marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from the taskList
        Task targetTask = tasks.getTask(this.getInput(), "unmark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Unmark the task
            targetTask.markAsNotDone();

            // Print out the statement
            ui.printText("OK, I've marked this task as not done yet:\n   " + targetTask.toString());

        } else {
            // Tell the user that the task is already marked as not done yet
            throw new JeffException("This task has already been marked as not done yet!");

        }
    }
}
