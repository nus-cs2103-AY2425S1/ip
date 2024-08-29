package sage.command;

import sage.ui.Ui;
import sage.task.TaskList;
import sage.task.Task;
import sage.task.ToDo;
import sage.exception.SageException;
import sage.storage.Storage;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) throws SageException {
        Task task = new ToDo(description);
        tasks.addTask(task);
        ui.showMessage("Great! I will add this task to the list:\n" + task +
                "\nNow you have " + tasks.size() +
                (tasks.size() > 1 ? " tasks" : " task") + " in your list");
        storage.saveTasks(tasks);
    }
}
