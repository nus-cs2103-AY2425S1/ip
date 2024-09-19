package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that creates a new deadlineTask in the taskList.
 *
 * @author Aaron
 */
public class DeadlineCommand extends Command {
    /**
     * The description of the deadlineTask to be created.
     */
    private String description;

    /**
     * The due date and time of the deadlineTask to be created.
     */
    private String dueBy;

    /**
     * Constructs a elsa.command.DeadlineCommand with the specified task description.
     *
     * @param description The description of the deadlineTask to be added.
     * @param dueBy The due date and time of the deadlineTask to be added.
     */
    public DeadlineCommand(String description, String dueBy) {
        this.description = description;
        this.dueBy = dueBy;
    }

    /**
     * Executes the command to add a new deadlineTask to the task list.
     *
     * @param tasks The task list where the new deadlineTask will be added.
     * @param ui The Ui instance, which is not used in this command but is included for method signature consistency.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.addDeadline(description, dueBy);
    }
}
