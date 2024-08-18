package commands;

import common.Command;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

public class MarkCommand extends Command {
    private String taskInput;

    public MarkCommand(String taskInput) {
        this.taskInput = taskInput;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            int taskIndex = Integer.parseInt(taskInput) - 1; // Convert to 0-based index
            if (taskIndex < 0 || taskIndex >= storage.getTasks().size()) {
                ui.printMessage("Invalid task number.");
            } else {
                Task task = storage.getTask(taskIndex);
                task.markAsDone();
                ui.printMessage("Nice! I've marked this task as done:\n  " + task);
            }
        } catch (NumberFormatException e) {
            ui.printMessage("Please enter a valid task number.");
        }
        return true;
    }
}
