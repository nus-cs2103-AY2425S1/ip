package luke.task;

/**
 * The {@code Event} class represents the Event task type. The Event task contains a task name, an event start time
 * {@code from}, and an event end time {@code to}.
 * <p>
 * The task description is shown in the format:
 * <pre>
 * [E][X] Task Name (from: Start Time to: End Time)
 * </pre>
 * where the "E" represents a "Event" task, and the "X" (or blank) represents the completion status.
 * </p>
 * The save format for an Event task is as follows:
 * <pre>
 * X | event | Task Name | from: Start Time to: End Time
 * </pre>
 * where "X" represents that the task is marked as done, and "-" indicates it is not done.
 * </p>
 *
 * @see Task
 * @see Todo
 * @see Event
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new {@code Event} task with the specified task name, start time, end time, and completion status.
     *
     * @param taskName The name of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The completion status of the event. {@code true} if the event is completed.
     */
    public Event(String taskName, String from, String to, boolean isDone) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String taskDescription() {
        return "[E]" + showMark() + " " + this.name
                + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | event | "
                + name + " from " + from + " to " + to + "\n";
    }
}
