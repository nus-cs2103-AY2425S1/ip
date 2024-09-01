package gray.command;

import gray.Tasks;
import gray.task.TodoTask;

/**
 * A command that adds a to-do task.
 */
public class AddTodoCommand implements Command {

    private final String description;

    /**
     * Constructs a command to add to-do task.
     * @param description
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add to-do task.
     *
     * @param tasks
     */
    @Override
    public String execute(Tasks tasks) {
        TodoTask task = new TodoTask(description);
        tasks.add(task);
        return String.format("""
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size());
    }
}
