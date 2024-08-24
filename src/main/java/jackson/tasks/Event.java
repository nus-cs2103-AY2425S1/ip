package jackson.tasks;

import jackson.utils.Temporal;
/**
 * Event class containing name, from date and to date.
 */
public class Event extends Task {
    private Temporal from;
    private Temporal to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = new Temporal(from);
        this.to = new Temporal(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(), this.from, this.to);
    }
}
