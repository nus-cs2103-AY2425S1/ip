package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Edith.task.Task " + index + " does not exist. Please enter a valid Edith.task number.");
        }

        Task taskToMark = tasks.getTask(index);
        taskToMark.markTaskDone();

        ui.showIndentedMessage("Alright, great job! I've marked Edith.task " + (index + 1) + " as done:");
        ui.showIndentedMessage(taskToMark.toString());
        ui.showLineBreak();

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated Edith.task list.");
        }
    }
}
