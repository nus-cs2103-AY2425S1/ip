public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + from + "|" + to;
    }

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + from + " to: " + to + ")";
    }
}
