package johncena.commands;

import johncena.exceptions.CenaInvalidTodoException;
import johncena.tasks.Task;
import johncena.tasks.Todo;
import johncena.storage.Storage;
import johncena.exceptions.CenaEmptyDescriptionException;

import java.util.ArrayList;

public class TodoCommand implements Command {
    private ArrayList<Task> tasks;
    private String description;

    public TodoCommand(ArrayList<Task> tasks, String description) {
        this.tasks = tasks;
        this.description = description;
    }

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

