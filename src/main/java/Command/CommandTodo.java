package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzException;

import task.Task;
import task.Todo;

public class CommandTodo extends Command {
    private String param;

    public CommandTodo(String command, String param) {
        super(command);
        this.param = param;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task task = new Todo(this.param, "T", false);

        list.addTask(task);
        storage.writeOneToFile(task);
        ui.printTaskAddedWithDivider("T", list.getSize(), task);
    }
}
