package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from the taskList
        Task targetTask = tasks.getTask(this.getInput(), "mark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Tell the user that the task is already marked as done
            throw new JeffException("This task has already been marked as done!");

        } else {
            // Mark the task as done
            targetTask.markAsDone();

            // Print out the statement
            ui.printText("OK, I've marked this task as done:\n   " + targetTask.toString());

        }
    }
}
