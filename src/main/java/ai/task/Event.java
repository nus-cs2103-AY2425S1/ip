package ai.task;

import java.util.Objects;

/**
 * A subtype of Task that has a from and to date.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private String from;
    private String to;

    /**
     * Creates a new Event object.
     * @param input String to be parsed in description, from, and to.
     */
    public Event(String input) {
        super(input.substring(0, input.indexOf("/from") - 1));
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        from = input.substring(indexFrom + 6, indexTo - 1);
        to = input.substring(indexTo + 4);
    }

    /**
     * Creates a new Event object.
     * @param desc String containing description.
     * @param from String containing starting time of the event.
     * @param to String containing ending time of the event.
     * @param isDone whether the task is done.
     */
    public Event(String desc, String from, String to, Boolean isDone) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", TASK_TYPE, super.toString(), from, to);
    }

    @Override
    public String stringData() {
        return String.format("%s | %s | %s - %s", TASK_TYPE, super.stringData(), from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event castedEvent = (Event) obj;

            return super.equals(obj)
                    && Objects.equals(this.from, castedEvent.from)
                    && Objects.equals(this.to, castedEvent.to);
        }
        return false;
    }
}
