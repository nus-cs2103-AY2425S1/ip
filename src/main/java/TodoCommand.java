/**
 * Represents the command that creates a new todoTask in the taskList.
 *
 * @author Aaron
 */
public class TodoCommand extends Command {
    /**
     * The description of the todoTask to be created.
     */
    private String description;

    /**
     * Constructs a TodoCommand with the specified task description.
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTodo(description);
    }
}
