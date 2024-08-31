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
            this.desc = description.split("[E][ ] ")[1].split(" (")[0];
            this.from = description.split("(from: ")[1].split(" to:")[0];
            this.to = description.split("(to: ")[1].split(")")[0];
            this.isDone = false;
        } else if (description.startsWith("[E][X] ")) {
            this.desc = description.split("[E][X] ")[1].split(" (")[0];
            this.from = description.split("(from: ")[1].split(" to:")[0];
            this.to = description.split("(to: ")[1].split(")")[0];
            this.isDone = true;
        }
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.desc + " (from: " + from + " to: " + to + ")";
    }
}
