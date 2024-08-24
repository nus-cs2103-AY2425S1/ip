public class Event extends Task {

    protected String desc;
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        this.desc = description.split("event ")[1].split(" /from")[0];
        this.from = description.split("/from ")[1].split(" /to")[0];
        this.to = description.split("/to ")[1];
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.desc + " (from: " + from + " to: " + to + ")";
    }
}
