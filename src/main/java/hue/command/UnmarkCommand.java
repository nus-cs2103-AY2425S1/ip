package hue.command;

import hue.HueException;
import hue.UI.UI;
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
            throw new HueException("Please give a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException, HueException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.unmarkDone();

            ui.showLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);

            storage.saveTasks(tasks);
        } else {
            throw new HueException("Task number is out of range.");
        }
    }

}
