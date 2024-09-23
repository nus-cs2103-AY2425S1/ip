package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Todo;
import hien.ui.UI;

/**
 * Represents a command to add a Todo task to the task list.
 * When executed, it adds the specified Todo task to the task list.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Constructs a TodoCommand object.
     *
     * @param input  The input string containing the description of the Todo task.
     * @param isExit Specifies whether this command will terminate the application.
     */
    public TodoCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }

    /**
     * Executes the command to add a Todo task to the task list.
     *
     * @param tasks   The task list where the Todo task will be added.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save the updated task list.
     * @return        A string message indicating the result of adding the Todo task.
     * @throws HienException If the description of the Todo task is empty.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return this.addTodo(tasks, input, storage, ui);
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param tasks      The task list where the Todo task will be added.
     * @param input      The input string containing the description of the Todo task.
     * @param storage    The storage object used to save the updated task list.
     * @param ui         The UI object used to interact with the user.
     * @return           A string message indicating the result of adding the Todo task.
     * @throws HienException If the description of the Todo task is empty.
     */
    private String addTodo(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String description = input.substring(4).trim();
        String msg = "";
        if (description.isEmpty()) {
            throw new HienException("☹ OOPS!!! The description of todo cannot be empty");
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        msg += ui.showMessage(" Got it. I've added this task:");
        msg += ui.showMessage("   " + todo);
        msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        return msg;
    }
}
