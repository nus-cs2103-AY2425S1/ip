package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Todo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(todo);
    }

    @Override
    public String toString() {
        return "todo command " + description;
    }
}
