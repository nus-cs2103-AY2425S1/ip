package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Edith.task.Task " + index + " does not exist. Please enter a valid Edith.task number.");
        }

        Task taskToDelete = tasks.getTask(index);
        tasks.deleteTask(index);

        ui.showIndentedMessage("Certainly. I've removed this Edith.task:");
        ui.showIndentedMessage(taskToDelete.toString());
        ui.showIndentedMessage("There are now " + tasks.getNumOfTasks() + " tasks in your list.");
        ui.showLineBreak();

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated Edith.task list.");
        }
    }
}
