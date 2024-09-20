package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaEmptyDescriptionException;
import johncena.exceptions.CenaInvalidTodoException;
import johncena.storage.Storage;
import johncena.tasks.Task;
import johncena.tasks.Todo;


/**
 * The {@code TodoCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "todo" command, which adds a todo task to the task list.
 */
public class TodoCommand implements Command {
    private ArrayList<Task> tasks;
    private String description;

    /**
     * Constructs a new {@code TodoCommand} with the specified task list and description.
     *
     * @param tasks the list of tasks
     * @param description the description of the todo task
     */
    public TodoCommand(ArrayList<Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

    /**
     * Executes the "todo" command. Adds a todo task to the task list.
     *
     * @throws CenaEmptyDescriptionException if the description of the todo is empty
     * @throws CenaInvalidTodoException if the description of the todo is invalid
     */
    @Override
    public void execute() throws CenaEmptyDescriptionException, CenaInvalidTodoException {
        if (description.isEmpty()) {
            throw new CenaEmptyDescriptionException("The description of a todo cannot be empty.");
        }
        if (description.contains("/by") || description.contains("/from") || description.contains("/to")) {
            throw new CenaInvalidTodoException("A todo should not contain /by, /from, or /to.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}

