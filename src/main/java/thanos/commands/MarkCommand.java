package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.input.isEmpty()) {
            throw new InvalidCommandException(
                    "No task index provided. Please use the correct format: 'mark [task index]'"
            );
        }

        if (this.input.split(" ").length != 1) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'mark [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(this.input) - 1;
            Task task = taskList.mark(index);
            ui.displayTaskMarked(task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }
}
