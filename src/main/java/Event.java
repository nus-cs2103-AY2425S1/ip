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
        String str = this.completed ? "[E][X] " : "[E][ ] ";
        str += String.format("%s (from:%s to:%s)", this.description, this.from, this.to);
        return str;
    }
}
