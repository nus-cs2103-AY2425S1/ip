package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that creates a new todoTask in the taskList.
 *
 * @author Aaron
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs an elsa.command.TodoCommand with the specified task description.
     *
     * @param description The description of the todoTask to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new todoTask with the specified description to the task list.
     *
     * @param tasks The task list where the new todoTask will be added.
     * @param ui The Ui instance, which is not used in this command but is included for method signature consistency.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.addTodo(description);
    }
}
