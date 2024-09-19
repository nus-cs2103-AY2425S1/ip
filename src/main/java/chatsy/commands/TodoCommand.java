package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.tasks.TodoTask;

/**
 * Handles the "todo" command which adds a new todo task.
 */
public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException();
        }
        taskManager.addTask(new TodoTask(description));
        return "Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.";
    }
}

