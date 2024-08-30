package chatbot.impl.tasks;

public class EventTask extends AbstractTask {

    private final String startTime;

    private final String endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static EventTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 5 || !parts[0].equals("E")) {
            throw new IllegalArgumentException("Invalid EventTask format");
        }

        EventTask eventTask = new EventTask(parts[2], parts[3], parts[4]);
        eventTask.setDone(parts[1].equals("1"));

        return eventTask;
    }

    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }

}
