public class Event extends Task {

    protected String desc;
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        if (description.startsWith("event ")) {
            this.desc = description.split("event ")[1].split(" /from")[0];
            this.from = description.split("/from ")[1].split(" /to")[0];
            this.to = description.split("/to ")[1];
        } else if (description.startsWith("[E][ ] ")) {
            String[] parts = description.split("\\[E\\]\\[ \\] ");
            this.desc = parts[1].split(" \\(from:")[0];
            String[] fromParts = parts[1].split("\\(from: ");
            this.from = fromParts[1].split(" to:")[0];
            String[] toParts = fromParts[1].split(" to: ");
            this.to = toParts[1].split("\\)")[0];
            this.isDone = false;
        } else if (description.startsWith("[E][X] ")) {
            String[] parts = description.split("\\[E\\]\\[X\\] ");
            this.desc = parts[1].split(" \\(from:")[0];
            String[] fromParts = parts[1].split("\\(from: ");
            this.from = fromParts[1].split(" to:")[0];
            String[] toParts = fromParts[1].split(" to: ");
            this.to = toParts[1].split("\\)")[0];
            this.isDone = true;
        }

    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.desc + " (from: " + from + " to: " + to + ")";
    }
}
