package seedu.avo.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;

public class SearchDateCommand extends Command {
    private final TaskManager manager;
    public SearchDateCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        LocalDate searchDate = DateTime.parseWithoutTime(inputs[1]);
        manager.getTasksByDate(searchDate);
    }
}
