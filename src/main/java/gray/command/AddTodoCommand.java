package gray.command;

import gray.TaskList;
import gray.Ui;
import gray.task.TodoTask;

/**
 * A command that adds a to-do task.
 */
public class AddTodoCommand extends Command {

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
     * @param ui
     * @param tasks
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        TodoTask task = new TodoTask(description);
        tasks.add(task);
        ui.say(String.format("""
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
