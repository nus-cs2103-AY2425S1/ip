package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.tasks.TodoTask;

/**
 * Handles the "todo" command which adds a new todo task to the task manager.
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a {@code TodoCommand} with a description of the task.
     *
     * @param description The description of the todo task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by adding a new {@link TodoTask} to the task manager.
     *
     * @param taskManager The task manager where the new todo task is added.
     * @return A string response confirming the task has been added.
     * @throws EmptyDescriptionException If the description is empty or blank.
     */
    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException("The description of a todo task cannot be empty.");
        }
        taskManager.addTask(new TodoTask(description));
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                description, taskManager.getTasks().size());
    }
}
