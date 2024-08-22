package Commands;

import Exceptions.AvoException;
import Tasks.Deadline;
import Tasks.TaskManager;

public class DeadlineCommand extends Command {
    private final TaskManager manager;
    public DeadlineCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("deadline |/by ");
        if (inputs.length < 3) {
            throw new AvoException("OOPS!!! The description of a deadline cannot be empty.");
        }
        manager.addTask(new Deadline(inputs[1], inputs[2]));
    }
}
