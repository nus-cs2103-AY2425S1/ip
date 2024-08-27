package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;

import java.time.LocalDate;

public class SearchNameCommand extends Command{
    private final TaskManager manager;
    public SearchNameCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ", 2);
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        manager.getTasksByName(inputs[1]);
    }
}
