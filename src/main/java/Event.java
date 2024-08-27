public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + this.start + " to: "+ this.end + ")";
    }

    public String toFileFormat() {
        return "E | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription() +
                " | " + this.start + " to " + this.end;
    }

    public static Event parseTask(String taskData) {
        if (taskData.startsWith("E |")) {
            String[] parts = taskData.split(" \\| ");
            String description = parts[2].trim();
            String fromTo = parts[3].trim();
            String[] fromToParts = fromTo.split(" to ");
            String from = fromToParts[0].trim();
            String to = fromToParts[1].trim();
            Event event = new Event(description, from, to);
            if (parts[1].trim().equals("1")) {
                event.markDone();
            }
            return event;
        }
        throw new IllegalArgumentException("Invalid Event format");
    }
}
