package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.Event;
import yapmeister.task.Task;
import yapmeister.task.TaskList;

/**
 * Represents Event user command that creates an Event Task
 */
public class EventCommand implements Command {
    private Task task;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        tasks.addTask(task);
        ui.displayString("Added:");
        ui.displayString(String.format("You have %d tasks", tasks.getSize()));
    }

    @Override
    public Command parse(String input) throws Exception {
        String[] inputs = input.split("event | /from | /to ");
        if (inputs.length <= 3) {
            throw new InvalidInputException("Invalid Event Task description");
        }
        task = new Event(inputs[1], inputs[2], inputs[3]);
        return this;
    }
}
