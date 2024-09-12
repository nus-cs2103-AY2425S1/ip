package bob;

/**
 * Represents a command to create an event task with a specified name, start date, and end date.
 * This command adds a new event task to the task list when run.
 */
public class EventCommand extends Command {
    protected static String[] params = new String[] {
        "event",
        "/from",
        "/to"
    };
    protected static int paramCount = 3;
    private String name;
    private String from;
    private String to;

    /**
     * Constructs an {@code EventCommand} with the specified task name, start date, and end date.
     *
     * @param name the name of the event task.
     * @param from the start date of the event.
     * @param to the end date of the event.
     */
    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new event task and adding it to the task list.
     * Displays a message indicating that the task has been added and shows the updated task list size.
     *
     * @param tasks the {@code TaskList} to which the event task will be added.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} for saving or loading tasks (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = tasks.event(this.name, this.from, this.to);
        return Printer.format(new String[] { "Got it. I've added this task:",
            " " + event.toString(),
            String.format("Now you have %d tasks in the list.", tasks.getSize()) });
    }

    /**
     * Compares this {@code EventCommand} with another object for equality.
     * Two {@code EventCommand} objects are considered equal if they have the same name, start date, and end date.
     *
     * @param obj the object to be compared with this {@code EventCommand}.
     * @return {@code true} if the specified object is equal to this {@code EventCommand}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand temp) {
            return this.name.equals(temp.name) && this.from.equals(temp.from) && this.to.equals(temp.to);
        }
        return false;
    }
}
