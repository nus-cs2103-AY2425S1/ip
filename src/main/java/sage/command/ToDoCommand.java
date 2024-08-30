package sage.command;

import sage.ui.Ui;
import sage.task.TaskList;
import sage.task.Task;
import sage.task.ToDo;
import sage.exception.SageException;
import sage.storage.Storage;

/**
 * Represents a command to add a todo task to the task list
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructs a ToDoCommand object with the specified task description
     *
     * @param description The description of the todo task
     * @throws SageException If the description is null or empty
     */
    public ToDoCommand(String description) throws SageException {
        if (description == null || description.trim().isEmpty()) {
            throw new SageException("The description of todo task cannot be empty!");
        }
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
