package sumode.task;

/**
 * A class for tasks with specific timeframe.
 */
public class Event extends Task {

    private final DueFromToFormat start;
    private final DueFromToFormat end;

    /**
     * Constructor for Event
     *
     * @param name Name of task.
     * @param start Starting date of task.
     * @param end Ending date of task
     */
    public Event(String name, String start, String end) {

        super(name);
        this.start = new DueFromToFormat(start);
        this.end = new DueFromToFormat(end);

    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + start
                + " to: "
                + end
                + ")";
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    @Override
    public String savedString() {
        return "E | " + super.savedString() + " | " + this.start + " | " + this.end;
    }
}
