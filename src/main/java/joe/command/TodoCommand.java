package joe.command;

import java.util.Objects;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.Todo;

/**
 * This class represents the 'todo' command.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new {@code TodoCommand} with the specified task description.
     *
     * @param description The description of the todo task, provided as a {@code String}. It should not be empty.
     * @throws JoeException if the provided description is empty.
     */
    public TodoCommand(String description) {
        if (description.isEmpty()) {
            throw new JoeException("OOPS!!! The description of a todo cannot be empty.");
        }
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
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        TodoCommand other = (TodoCommand) obj;
        return Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    public static String getHelp() {
        return "To add a new todo, try: todo {desc}";
    }
}
