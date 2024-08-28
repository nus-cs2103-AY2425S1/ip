package commands;

import exceptions.DownyException;
import exceptions.InvalidFormatException;
import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class TodoCommand implements Command {

    private final String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;

    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        Todo t = tasks.addTodo(taskDescription);
        storage.writeTodoToFile(t);
        ui.displayTaskAdded(t, tasks.getSize());
    }
    public boolean isExit() {
        return false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }
}
