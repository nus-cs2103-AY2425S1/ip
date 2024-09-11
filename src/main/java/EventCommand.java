/**
 * Represents the command that creates a new eventTask in the taskList.
 *
 * @author Aaron
 */
public class EventCommand extends Command {
    /**
     * The description of the eventTask to be created.
     */
    private String description;

    /**
     * The start date and time of the eventTask to be created.
     */
    private String start;

    /**
     * The end date and time of the eventTask to be created.
     */
    private String end;

    /**
     * Constructs a EventCommand with the specified task description.
     *
     * @param description The description of the eventTask to be added.
     * @param start The start date and time of the eventTask to be added.
     * @param end The end date and time of the eventTask to be added.
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add a new eventTask to the task list.
     *
     * @param tasks The task list where the new eventTask will be added.
     * @param ui The Ui instance, which is not used in this command but is included for method signature consistency.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addEvent(description, start, end);
    }
}
