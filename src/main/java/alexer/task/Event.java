package alexer.task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        type = TaskType.EVENT;
    }

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

    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s|%s|%s",
                getTaskType(), isDone ? 1 : 0, description, from, to);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (from: %s to: %s)",
                getTaskType(), super.toString(), from, to);
    }
}
