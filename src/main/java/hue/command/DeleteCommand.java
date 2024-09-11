package hue.command;

import hue.HueException;
import hue.UI.UI;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;


/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a {@code DeleteCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public DeleteCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number to delete");
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException, HueException {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "The task index is out of range";
        Task removedTask = tasks.remove(taskIndex);
        storage.saveTasks(tasks);
        return ui.showDeleteTask(removedTask, tasks.size());
    }
}
