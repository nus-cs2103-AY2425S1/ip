package optimus.tasks;

import java.util.Objects;

import optimus.Storage;

/**
 * Task with start and end time
 */
public class EventTask extends Task {
    private final String start;
    private final String end;

    /**
     * Constructor for when new EventTask is initialised
     *
     * @param desc  - description
     * @param start - start time
     * @param end   - end time
     */
    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for when EventTask is found in storage
     *
     * @param desc   - description
     * @param start  - start time
     * @param end    - end time
     * @param status - task status
     */
    public EventTask(String desc, String start, String end, String status) {
        this(desc, start, end);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    /**
     * Returns string representation of the task for UI purposes
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        String s = "";
        s += "[E]";
        s += super.toString();
        s += String.format(" (from: %s to: %s)", this.start, this.end);
        return s;
    }

    /**
     * Returns string representation of the task for storage purposes
     *
     * @return String representation of task
     */
    @Override
    public String getStorageString() {
        String s = "";
        s += "E";
        s += super.getStorageString();
        s += start;
        s += Storage.SPECIAL_CHAR;
        s += end;
        s += Storage.SPECIAL_CHAR;
        return s;
    }
}
