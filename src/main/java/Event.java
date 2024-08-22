import java.util.Objects;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String task, String from, String to) throws TheBotFatherException {
        super(task);
        if (Objects.equals(from, "")) {
            Task.COUNT--;
            throw new TheBotFatherException("No from");
        }
        this.from = from.substring(0, from.length() - 1);
        this.to = to.substring(0, to.length() - 1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
