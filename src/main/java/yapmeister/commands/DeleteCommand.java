package yapmeister.commands;

import static java.lang.Integer.parseInt;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.InvalidMarkException;
import yapmeister.task.Task;
import yapmeister.task.TaskList;


/**
 * Represents Delete user command that deletes the task at index
 */
public class DeleteCommand implements Command {
    private int index;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        if (this.index >= tasks.getSize() || this.index < 0) {
            throw new InvalidMarkException("No task at that index");
        }
        Task removedTask = tasks.deleteTask(this.index);
        ui.displayString("Removed this task");
        ui.displayString(removedTask.toString());
        ui.displayString(String.format("You have %d tasks", tasks.getSize()));
    }

    @Override
    public Command parse(String input) throws Exception {
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new InvalidInputException("Insufficient arguments for Delete");
        }
        try {
            this.index = parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Index not in correct number format");
        }
        return this;
    }
}
