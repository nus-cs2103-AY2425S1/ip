package command;

import fridayException.FridayException;
import fridayException.InvalidDeleteArgument;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidDeleteArgument();
            }
            Task taskToDelete = tasks.getTask(taskIndex);  // Get the task to be deleted
            tasks.removeTask(taskIndex);  // Remove the task from the list
            ui.showTaskRemoved(taskToDelete, tasks.size());  // Show the user that the task was removed

            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }
}
