package duch.task;

public class Event extends Task {
    Date from;
    Date to;

    public Event(String task, String from, String to, boolean done) {
        this(task, from, to);
        isDone = done;
    }

    public Event(String task, String from, String to) {
        super(task);
        this.from = new Date(from);
        this.to = new Date(to);
    }

    /**
     * Converts the event object to a string to be printed.
     * 
     * @return The string.
     */
    public String toString() {
        return (isDone) ? "[E][X] " + task + " (from: " + from.toString() + " to: " + to.toString() + ")" 
                : "[E][ ] " + task + " (from: " + from.toString() + " to: " + to.toString() + ")";
    }

    /**
     * Converts the event object to a string to be stored.
     * 
     * @return The string.
     */
    public String toStore() {
        return (isDone) ? "E|1|" + task + "|" + from.toStore() + "|" + to.toStore() 
                : "E|0|" + task + "|" + from.toStore() + "|" + to.toStore();
    }
}
