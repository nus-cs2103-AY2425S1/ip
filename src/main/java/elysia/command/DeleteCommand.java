package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a delete command in the application.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a delete command.
     *
     * @param index The 1-indexed position of displayed list.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command to remove a task from the list and update the storage.
     *
     * @param tasks The list of tasks from which a task will be removed.
     * @return A message indicating the result of the operation, including confirmation of the task removal.
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        Task task = tasks.get(index);
        assert task != null : "task is null";
        tasks.remove(index);

        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return ui.getDeleteTaskMessage(tasks, task);
    }
}
