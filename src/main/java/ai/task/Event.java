package ai.task;

/**
 * A subtype of Task that has a from and to date.
 */
public class Event extends Task {
    private final static String TASK_TYPE = "E";
    private String from;
    private String to;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/from") - 1));
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        from = input.substring(indexFrom + 6, indexTo - 1);
        to = input.substring(indexTo + 4);
    }

    public Event(String desc, String from, String to, Boolean isDone) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", TASK_TYPE, super.toString(), from, to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringData() {
        return String.format("%s | %s | %s - %s", TASK_TYPE, super.stringData(), from, to);
    }
}
