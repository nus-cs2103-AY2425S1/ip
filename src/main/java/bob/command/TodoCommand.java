package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Task;
import bob.task.Todo;

import java.util.Map;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";

    public TodoCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String desc = this.arguments.get("");
        if (desc == null || desc.isBlank()) {
            throw new MissingArgumentException("description of the todo");
        }

        Task task = new Todo(desc);
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
