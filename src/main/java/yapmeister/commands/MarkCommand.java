package yapmeister.commands;

import static java.lang.Integer.parseInt;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.InvalidMarkException;
import yapmeister.task.TaskList;

/**
 * Represents Mark user command that marks the task at index
 */
public class MarkCommand implements Command {
    private int index;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws InvalidMarkException {
        if (index >= tasks.getSize() || index < 0) {
            throw new InvalidMarkException("No task at that index");
        }
        tasks.getTask(index).setCompleted(true);
        ui.displayString("You did this:");
        ui.displayString(tasks.getTask(index).toString());
    }

    @Override
    public Command parse(String input) throws InvalidInputException {
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new InvalidInputException("Insufficient arguments for Mark");
        }
        try {
            this.index = parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Index not in correct number format");
        }
        return this;
    }
}
