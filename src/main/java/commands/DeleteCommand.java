package commands;

import exceptions.InvalidCommandException;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.input.isEmpty()) {
            throw new InvalidCommandException(
                    "No task index provided. Please use the correct format: 'delete [task index]'"
            );
        }

        if (this.input.split(" ").length != 1) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'delete [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(this.input) - 1;
            Task task = taskList.remove(index);
            ui.displayTaskRemoved(task, taskList.size());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }
}
