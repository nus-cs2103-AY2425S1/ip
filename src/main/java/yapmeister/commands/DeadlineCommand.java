package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.Deadline;
import yapmeister.task.Task;
import yapmeister.task.TaskList;

/**
 * Represents Deadline user command that creates a Deadline Task
 */
public class DeadlineCommand implements Command {
    private Task task;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        tasks.addTask(task);
        ui.displayString("Added:");
        ui.displayString(String.format("You have %d tasks", tasks.getSize()));
    }

    @Override
    public Command parse(String input) throws Exception {
        String[] inputs = input.split("deadline | /by ");
        if (inputs.length <= 2) {
            throw new InvalidInputException("Invalid Deadline Task description");
        }
        task = new Deadline(inputs[1], inputs[2]);
        return this;
    }
}
