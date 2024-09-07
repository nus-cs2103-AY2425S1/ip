package taskpack;

/**
 * Used to represent an Event task.
 */
public class Event extends Task {

    private String start;
    private String end;

    /**
     * Constructor for Event task.
     * @param name Name of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Overloaded constructor method
     * @param name Name of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     * @param isCompleted Task completion status.
     */
    public Event(String name, String start, String end, boolean isCompleted) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method.
     * @return The String to be saved into the write file
     */
    @Override
    public String toParseableString() {
        String s = "e,";
        if (this.isCompleted()) {
            s += "m,";
        } else {
            s += "u,";
        }
        s += this.getName() + "," + this.start + "," + this.end;
        return s;
    }
}
