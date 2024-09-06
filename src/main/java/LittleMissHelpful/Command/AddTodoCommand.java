package LittleMissHelpful.Command;

import LittleMissHelpful.Storage;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Task.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.showAddedNewTask(todo, tasks);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
