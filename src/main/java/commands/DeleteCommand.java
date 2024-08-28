package commands;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to delete.");
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
            Task removedTask = tasks.remove(taskIndex);
            storage.saveTaskList(tasks.getTasks());

            ui.printLine();
            ui.show("Noted. I've removed this task:");
            ui.show(removedTask.toString());
            ui.show("Now you have " + tasks.size() + " tasks in the list.");
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
