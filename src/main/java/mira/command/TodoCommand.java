package mira.command;

import java.io.IOException;

import mira.Savable;
import mira.Storage;
import mira.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command implements Savable {
    private final Todo todo;

    /**
     * Constructs a {@code TodoCommand} with the specified description of the todo task.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Executes the command by adding the todo task to the task list.
     *
     * @return A message indicating that the task has been added.
     */
    @Override
    public String execute() {
        taskList.addTask(todo);
        return "Got it. I've added this task:\n  " + todo
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Saves the todo task to the specified storage.
     *
     * @param storage The storage to save the task to.
     * @throws IOException If there is an error in file operations.
     */
    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(todo);
    }
}
