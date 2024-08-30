package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.Todo;

/**
 * This class represents the todo command.
 */
public class TodoCommand extends Command {
    private final String description;
    public TodoCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        Task newTask = new Todo(this.description);
        taskList.addTask(newTask);
        ui.printAddedTask(newTask, taskList.getSize());
    }
}
