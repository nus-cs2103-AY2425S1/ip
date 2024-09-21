package sammy.command;

import java.io.IOException;

import sammy.task.TaskList;
import sammy.Ui;
import sammy.SammyException;
import sammy.Storage;
import sammy.task.Task;

public class TagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException {
        // Ensure the task index is within bounds
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new SammyException("Task index is out of bounds.");
        }

        // Get the task from the TaskList using the provided index
        Task task = tasks.get(taskIndex);

        // All tasks can now be tagged, so no need for instanceof checks
        task.addTag(tag);  // Add the provided tag to the task
        storage.save(tasks);  // Save the updated task list to storage
        return ui.showTaggedTask(task);  // Return the updated task as a string
    }

    @Override
    public boolean isExit() {
        return false; // TagCommand does not terminate the program
    }
}


