package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
import seedu.avo.tasks.ToDo;

public class TodoCommand extends Command {
    private final TaskManager manager;
    public TodoCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("todo ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The description of a todo cannot be empty.");
        }
        manager.addTask(new ToDo(inputs[1]));
    }
}
