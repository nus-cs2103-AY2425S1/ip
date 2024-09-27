package alexer.task;

public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates a new event task
     *
     * @param description The description of the task, inherited from Task class
     * @param from The start date/time of the event
     * @param to The end date/time for the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        type = TaskType.EVENT;
    }

    /**
     * Creates an event task from a task string, used
     * when loading tasks from the save file.
     *
     * @param taskString the raw task string saved to file
     * @return a new event task if the task string is valid, null otherwise
     */
    public static Event fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 5)
            return null;

        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markAsDone();
        }

        return event;
    }

    /** Returns the task type prefix **/
    protected String getTaskType() {
        return "E";
    }

    /**
     * Converts the task into the task string form for serialisation
     * and saving to file. This also ensures a balance between human readability
     * and ease of machine parsing
     *
     * @return the task string of the current task
     */
    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s|%s|%s",
                getTaskType(), isDone ? 1 : 0, description, from, to);
    }

    /**
     * Converts the task into string form for printing, and
     * primarily focuses on human readability.
     *
     * @return the human-readable string form of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (from: %s to: %s)",
                getTaskType(), super.toString(), from, to);
    }
}
