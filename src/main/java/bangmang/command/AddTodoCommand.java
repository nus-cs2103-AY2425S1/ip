package LittleMissHelpful.Command;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Tasks.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        return ui.showAddedNewTask(todo, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
