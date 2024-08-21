package todo;

/**
 * A class representing individual events
 * happening within the interval
 * [from, to]
 *
 * @author celeschai
 * @version 1.0 22 Aug 2023
 */
public class Event extends Task{
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.from,
                this.to);
    }
}
