package seedu.avo.commands;

import java.time.LocalDateTime;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Event;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;

/**
 * Represents the command to add an event task
 */
public class EventCommand extends Command {
    private static final int INPUT_SIZE = 4;
    private final TaskManager manager;
    public EventCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("event |/from |/to ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The description of an event cannot be empty.");
        }
        LocalDateTime startTime = DateTime.parseWithTime(inputs[2]);
        LocalDateTime endTime = DateTime.parseWithTime(inputs[3]);
        String message = manager.addTask(new Event(inputs[1].trim(), startTime, endTime));
        return new CommandResult(message);
    }
}
