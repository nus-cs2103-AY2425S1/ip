package commands;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command handles the unmarking of a task at a specified index, indicating it is not completed.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to unmark.");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InputException("Invalid task number format. Please enter a valid number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).unmark();
            storage.saveTaskList(tasks.getTasks());

            ui.printLine();
            ui.show("OK, I've marked this task as not done yet:");
            ui.show(tasks.get(taskIndex).toString());
            ui.printLine();
        } else {
            ui.show("The specified task does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
