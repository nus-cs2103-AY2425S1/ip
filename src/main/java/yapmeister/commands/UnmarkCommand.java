package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.InvalidMarkException;
import yapmeister.task.TaskList;

import static java.lang.Integer.parseInt;

public class UnmarkCommand implements Command {
    private int index;
    public void execute(TaskList tasks, Storage storage, UI ui) throws InvalidMarkException {
        if (index >= tasks.getSize() || index < 0) {
            throw new InvalidMarkException("No task at that index");
        }
        tasks.get(index).setCompleted(true);
        ui.displayString("You did this:");
        ui.displayString(tasks.get(index).toString());
    }

    @Override
    public Command parse(String input) throws InvalidInputException {
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new InvalidInputException("Insufficient arguments for Unmark");
        }
        try {
            this.index = parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Index not in correct number format");
        }
        return this;
    }
}
