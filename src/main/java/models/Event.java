package models;

public class Event extends Task {
    public static final char TASK_TYPE = 'E';

    private final String from;
    private final String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public static Event deserialize(String line) {
        String[] values = line.split("\\|");
        Event event = new Event(values[2], values[3], values[4]);
        if (values[1].equals("X")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.from,
            this.to
        );
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", this.from, this.to);
    }
}
