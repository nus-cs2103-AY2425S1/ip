package myapp.command;

import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.task.ToDo;

/**
 * Represents a command that adds a new {@link ToDo} task to the task list.
 * This command stores the description of the ToDo task and adds it to the list when executed.
 */
public class ToDoCommand extends AddCommand {

    /**
     * Constructs a ToDoCommand with the specified task description.
     *
     * @param s The description of the ToDo task.
     */
    public ToDoCommand(String s) {
        super(s);
    }

    /**
     * Executes the command by adding a new {@link ToDo} task to the task list.
     * The task is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list to which the ToDo task will be added.
     * @param storage The storage system used to save the task list.
     * @return A string message confirming the addition of the ToDo task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = new ToDo(description);
        tasks.add(task);
        saveTasks(tasks, storage);
        return printAddMessage(tasks, task);
    }
}
