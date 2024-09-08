package commands;

import java.io.IOException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String taskInput;

    /**
     * Creates a new MarkCommand.
     *
     * @param taskInput The input string containing the task number to be marked.
     */
    public MarkCommand(String taskInput) {
        this.taskInput = taskInput.substring(5).trim();
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        try {
            int taskIndex = Integer.parseInt(taskInput) - 1; // Convert to 0-based index
            if (taskIndex < 0 || taskIndex >= storage.getTasks().size()) {
                return ui.outputMessage("Invalid task number.");
            } else {
                Task task = storage.getTask(taskIndex);
                task.markAsDone();
                storage.saveTasks();
                return ui.outputMessage("Nice! I've marked this task as done:\n  " + task);
            }
        } catch (NumberFormatException e) {
            return ui.outputMessage("Please enter a valid task number.");
        } catch (IOException e) {
            return ui.outputMessage("An error occurred while saving tasks.");
        }
    }
}
