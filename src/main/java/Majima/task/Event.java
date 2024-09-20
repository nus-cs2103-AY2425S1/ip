package Majima.task;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Parses the input from the user's console. If valid, adds a task of type Event
     * to the list and .txt file, by calling other methods, such as those in Majima.Parse.
     *
     * @param description String Description of the Event
     * @param from String that denotes the start of the Event
     * @param to String that denotes the end of the Event
     */
    public Event(String description, String from, String to) {
        super(description, Task.TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "[E] | " + getStatusIcon() + " | "
                + getDescription() + " | " + from + " | " + to;
    }
}
