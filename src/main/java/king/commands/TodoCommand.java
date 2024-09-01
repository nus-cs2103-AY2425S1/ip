package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;
import king.task.Todo;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}