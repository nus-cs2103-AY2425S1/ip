package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class AddTodoCommand extends Command {
    private final Todo todo;

    public AddTodoCommand(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("Todo is missing description!");
        }
        // argument is taskDescription
        this.todo = new Todo(arguments);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.todo);
    }
}
