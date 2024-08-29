package command;

import exception.ExecuteCommandException;
import exception.FileException;
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
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        tasks.addTask(new Todo(this.description));
        storage.saveTaskToFile(tasks);
        // TODO: need to print smt?
    }

    @Override
    public String toString() {
        return "todo command " + description;
    }
}
