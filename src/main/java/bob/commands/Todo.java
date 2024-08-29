package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;
import bob.tasks.Task;
import bob.tasks.TodoTask;

/**
 * Represents a command that adds a todo task to the list.
 */
public class Todo extends Command {
    String description;
    public Todo(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task to the list.
     * Shows the user the task that has been added and the number of tasks in the list.
     *
     * @param list The list of tasks.
     * @param task The task to be added.
     */
    private static void taskAdded(TaskList list, Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = new TodoTask(description);
        list.add(t);
        taskAdded(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
