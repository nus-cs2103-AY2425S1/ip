package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.tasks.TodoTask;
import bob.ui.Ui;

/**
 * Class representing the todo command.
 */
public class Todo extends Command {
    private String description;
    public Todo(String description) {
        this.description = description;
    }

    private static String addTask(TaskList list, Task t) {
        String response = "Got it. I've added this task:\n" + t
                    + "Now you have " + list.size() + (list.size() == 1 ? " task in the list."
                    : " tasks in the list.");
        return response;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        Task t = new TodoTask(description);
        list.add(t);
        return addTask(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
