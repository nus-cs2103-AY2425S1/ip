public class Event extends Task {
    private String start;
    private String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString() + String.format("( from: %sto: %s)", this.start, this.end);
        return str;
    }
}
