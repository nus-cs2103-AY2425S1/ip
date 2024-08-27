package Command;

/* My import */
import Blitz.Storage;
import Blitz.TaskList;
import Blitz.Ui;
import Exception.BlitzException;
import Task.Task;
import Task.Todo;

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
        ui.printTaskAddedWithDivider("T", list.size(), task);
    }
}
