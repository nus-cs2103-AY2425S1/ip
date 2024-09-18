package muke.command;

import java.io.IOException;

import muke.storage.Storage;
import muke.task.Task;
import muke.task.TaskList;
import muke.ui.Ui;

/**
 * Represents a command to edit an existing task.
 */
public class EditCommand implements Command {
    private final int taskIndex;
    private final String updatedDescription;

    /**
     * Constructs an EditCommand.
     *
     * @param taskIndex          The index of the task to edit.
     * @param updatedDescription The updated description of the task.
     */
    public EditCommand(int taskIndex, String updatedDescription) {
        this.taskIndex = taskIndex;
        this.updatedDescription = updatedDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            ui.showError("Invalid task number.");
            return;
        }

        Task taskToEdit = tasks.getTask(taskIndex);
        taskToEdit.setDescription(updatedDescription);
        ui.showEditSuccess(taskToEdit);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("An error occurred while saving your tasks.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}