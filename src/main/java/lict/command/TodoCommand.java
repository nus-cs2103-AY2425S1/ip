package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.LictException;
import lict.task.Task;
import lict.task.Todo;


/**
 * The {@code TodoCommand} class is responsible for handling the addition of a new todo task.
 * It creates a new {@code Todo} object and adds it to the task list, then updates the UI and storage.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * The {@code TodoCommand} class is responsible for handling the addition of a new todo task.
     * It creates a new {@code Todo} object and adds it to the task list, then updates the UI and storage.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        if (description.isEmpty()) {
            throw new LictException("OOPS!!! The description of a todo cannot be empty. Please ensure that your input is in the format: todo {description}");
        }
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.save(tasks);
    }
}
