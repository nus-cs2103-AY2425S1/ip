package command;

import java.io.IOException;
import java.util.ArrayList;

import exception.ScheduloException;
import task.Task;
import task.TaskList;
import task.Todo;
import util.Storage;

/**
 * The AddMultipleTodosCommand class represents a command to add multiple Todo tasks to the task list.
 * It extends the Command class and provides the implementation for adding multiple todos.
 */
public class AddMultipleTodosCommand extends Command {
    private ArrayList<Task> todos;

    /**
     * Constructs an AddMultipleTodosCommand with a variable number of task names.
     *
     * @param todos A variable number of task names to create Todo tasks.
     */
    public AddMultipleTodosCommand(String... todos) {
        this.todos = new ArrayList<>();
        for (String name : todos) {
            this.todos.add(new Todo(name));
        }
    }

    /**
     * Executes the AddMultipleTodosCommand by adding multiple
     * Todo tasks to the task list and saving the updated list to storage.
     *
     * @param tasks   The TaskList where the Todo tasks will be added.
     * @param storage The Storage instance used to save the updated task list.
     * @return A message indicating the tasks that were added.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        String message = tasks.addMultipleTodos(todos);
        storage.save(tasks);
        return message;
    }

}
