package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Task;
import bob.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        if (argument.isBlank()) {
            throw new MissingArgumentException("description of the todo");
        }

        Task task = new Todo(argument.strip());
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
