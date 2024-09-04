package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.Task;
import yapmeister.task.TaskList;
import yapmeister.task.ToDo;

/**
 * Represents Todo user command that creates an Todo Task
 */
public class TodoCommand implements Command {
    private Task task;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        tasks.addTask(task);
        ui.displayString("Added:");
        ui.displayString(String.format("You have %d tasks", tasks.getSize()));
    }

    @Override
    public Command parse(String input) throws Exception {
        String[] inputs = input.split("todo ");
        if (inputs.length <= 1) {
            throw new InvalidInputException("Invalid Todo Task description");
        }
        task = new ToDo(inputs[1]);
        return this;
    }
}
