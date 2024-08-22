package Commands;

import Exceptions.AvoException;
import Tasks.Event;
import Tasks.TaskManager;

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
        manager.addTask(new Event(inputs[1], inputs[2], inputs[3]));
    }
}
