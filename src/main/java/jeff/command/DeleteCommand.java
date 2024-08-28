package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from taskList
        Task targetTask = tasks.getTask(this.getInput(), "delete ");

        // Delete the task
        tasks.remove(targetTask);

        ui.printText("Noted. I've removed this task:\n   " + targetTask.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }
}
