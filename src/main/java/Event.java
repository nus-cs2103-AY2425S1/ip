public class Event extends Task{
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        String str = this.isDone ? "[E][X] " : "[E][ ] ";
        str += this.description.trim() + " (from: " + this.from + " to: " + this.to + ")";
        return str;
    }
}
