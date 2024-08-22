/**
 * Represents an Event task.
 * An Event task is a task that has a specific start and end time, in addition to a description.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected String from;

    /** The end time of the event. */
    protected String to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description the description of the Event task.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event task from the given input string.
     * The input string must start with the command "event" followed by the task description,
     * the start time, and the end time.
     *
     * @param input the input string containing the task details.
     * @return the created Event task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("event")) {
            throw new InputException("To add an Event task, use the following format: event <task description> /from <start time> /to <end time>");
        }
        String[] details = input.substring(6).split(" /from | /to ");
        if (details.length == 3) {
            String description = details[0].trim();
            String from = details[1].trim();
            String to = details[2].trim();
            if (description.isEmpty()) {
                throw new InputException("You need to describe your Event!");
            }
            return new Event(description, from, to);
        } else {
            throw new InputException("Invalid format. Use: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes a "[E]" prefix to indicate that it is an Event task, followed by the task status,
     * description, start time, and end time.
     *
     * @return a string representing the Event task.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
