package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.Todo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        TodoCommand other = (TodoCommand) obj;
        return Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

}
