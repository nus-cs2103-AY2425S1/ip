package command;

import fridayException.FridayException;
import fridayException.InvalidMarkArgument;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidMarkArgument();
            }
            Task taskToMark = tasks.getTask(taskIndex);  // Get the task to be marked
            tasks.markTask(taskIndex);  // Mark the task as completed
            ui.showTaskMarked(taskToMark);  // Show the user that the task was marked

            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }
}
