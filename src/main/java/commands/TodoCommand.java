package main.java.commands;

import main.java.TaskList;
import main.java.Todo;
import main.java.util.Storage;
import main.java.util.Ui;

public class TodoCommand extends Command{
    private Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(todo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
