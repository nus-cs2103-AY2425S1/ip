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
     * Constructs a DeadlineCommand with the specified task description.
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addDeadline(description, dueBy);
    }
}
