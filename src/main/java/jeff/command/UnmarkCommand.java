package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

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
