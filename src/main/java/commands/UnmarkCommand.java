package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private String taskInput;

    public UnmarkCommand(String taskInput) {
        this.taskInput = taskInput.substring(7).trim();
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) throws SkibidiException {
        try {
            int taskIndex = Integer.parseInt(taskInput) - 1; // Convert to 0-based index
            if (taskIndex < 0 || taskIndex >= storage.getTasks().size()) {
                ui.printMessage("Invalid task number.");
            } else {
                Task task = storage.getTask(taskIndex);
                task.markAsUndone();
                storage.saveTasks();
                ui.printMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
        } catch (NumberFormatException e) {
            throw new SkibidiException("Please enter a valid task number.");
        } catch (IOException e) {
            throw new SkibidiException("An error occurred while saving tasks.");
        }
        return true;
    }
}
