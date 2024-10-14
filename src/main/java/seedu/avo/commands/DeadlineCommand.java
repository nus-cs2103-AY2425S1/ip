package seedu.avo.commands;

import java.time.LocalDateTime;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Deadline;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;
/**
 * Represents the command to add a deadline task
 */
public class DeadlineCommand extends Command {
    private static DeadlineCommand instance;
    private static final int INPUT_SIZE = 3;
    private final TaskManager manager;
    private DeadlineCommand(TaskManager manager) {
        this.manager = manager;
    }

    /**
     * Returns a singleton instance of DeadlineCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of DeadlineCommand
     */
    public static DeadlineCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new DeadlineCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("deadline |/by ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The description of a deadline cannot be empty.");
        }
        LocalDateTime dueDate = DateTime.parseWithTime(inputs[2]);
        String message = manager.addTask(new Deadline(inputs[1].trim(), dueDate));
        return new CommandResult(message, false);
    }
}
