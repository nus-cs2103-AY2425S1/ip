package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Todo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        tasks.addTask(new Todo(this.description));
        // TODO: need to print smt?
    }

    @Override
    public String toString() {
        return "todo command " + description;
    }
}
