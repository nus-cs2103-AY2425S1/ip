package simon;
/**
 * Represents a command to add an Event task to the task list.
 * Implements the Command interface to define the execution behavior for adding Event tasks.
 */
public class EventCommand implements Command {
    private final String name;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified task name, start time, and end time.
     *
     * @param name the name of the event task
     * @param from the start time of the event as a string
     * @param to the end time of the event as a string
     */
    public EventCommand(String name, String from,
                        String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return this.name;
    }
    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }

    /**
     * Executes the command to add an Event task to the task list.
     * The task is added to the task list, displayed to the user, and saved to the storage.
     *
     * @param taskList the list of tasks to which the new event task will be added
     * @param ui the user interface used to show feedback to the user
     * @param storage the storage used to save the updated task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Events task = new Events(name, taskList.size(), from, to);
        taskList.add(task);
        String ret = ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());
        return ret;

    }
}
