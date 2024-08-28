package hue.command;


import hue.HueException;
import hue.UI.UI;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;
/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public final int taskIndex;

    /**
     * Creates a {@code MarkCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public MarkCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please give a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HueException, IOException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markDone();

            ui.showLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);

            storage.saveTasks(tasks);
        } else {
            throw new HueException("Task number is out of range.");
        }
    }
}
