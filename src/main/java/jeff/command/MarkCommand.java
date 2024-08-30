package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Represents a "Mark task as done" command.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for MarkCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Gets the target task from the task list and mark it as done.
     * Also prints out a statement to inform the user.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format or if the task has already been marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from taskList
        Task targetTask = tasks.getTask(this.getInput(), "mark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Tell the user that it already is done
            throw new JeffException("This task has already been marked as done!");

        } else {
            // Mark the task as done
            targetTask.markAsDone();

            ui.printText("OK, I've marked this task as done:\n   " + targetTask.toString());
        }
    }
}
