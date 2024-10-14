package seedu.avo.commands;

import java.time.LocalDate;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;
/**
 * Represents the command to search tasks by date
 */
public class SearchDateCommand extends Command {
    private static SearchDateCommand instance;
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    private SearchDateCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of SearchDateCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of SearchDateCommand
     */
    public static SearchDateCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new SearchDateCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        LocalDate searchDate = DateTime.parseWithoutTime(inputs[1]);
        String message = manager.getTasksByDate(searchDate);
        return new CommandResult(message, false);
    }
}
