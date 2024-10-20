package hue.command;

import hue.Hue;
import hue.HueException;
import hue.ui.Ui;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates an {@code UnmarkCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public UnmarkCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please give a valid task number to unmark.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, HueException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new HueException("Please give a valid task number to unmark.");
        }
        Task task = tasks.get(taskIndex);
        if (!task.hasDone()) {
            throw new HueException("Task has not been done yet");
        }
        task.unmarkDone();
        storage.saveTasks(tasks);
        return ui.showUnmarkTask(task);
    }

}
