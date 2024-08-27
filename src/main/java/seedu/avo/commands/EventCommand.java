package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Event;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final TaskManager manager;
    public EventCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("event |/from |/to ");
        if (inputs.length < 4) {
            throw new AvoException("OOPS!!! The description of an event cannot be empty.");
        }
        LocalDate startTime = DateTime.parse(inputs[2]);
        LocalDate endTime = DateTime.parse(inputs[3]);
        manager.addTask(new Event(inputs[1], startTime, endTime));
    }
}
