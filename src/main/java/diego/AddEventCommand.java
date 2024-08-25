package diego;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand implements Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new AddEventCommand.
     *
     * @param description The description of the event task.
     * @param from        The start time/date of the event.
     * @param to          The end time/date of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

     /**
     * Executes the command to add an event task to the task list.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}