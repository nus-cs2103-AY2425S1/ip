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

    @Override
    public String toStringinFile() {
        int val = this.completed ? 1 : 0;
        return String.format("E|%d|%s|%s|%s", val, this.description, this.from, this.to);
    }
}
