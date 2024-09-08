package task;

/**
 * Represents an Event type Task with a start time and an end time.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates an Event task with the specified name, start time, and end time.
     *
     * @param name The name of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);

        String[] startParts = startTime.split(" ");
        StringBuilder startSB = new StringBuilder(startParts[0]).append(" ");
        for (int i = 1; i < startParts.length; i++) {
            startSB.append(startParts[i]).append(" ");
        }
        this.startTime = startSB.toString();

        String[] endParts = endTime.split(" ");
        StringBuilder endSB = new StringBuilder(endParts[0]).append(" ");
        for (int i = 1; i < endParts.length - 1; i++) {
            endSB.append(endParts[i]).append(" ");
        }
        if (endParts.length > 1) {
            endSB.append(endParts[endParts.length - 1]);
        }

        this.endTime = endSB.toString();
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "E , {0 if not complete, 1 if complete} , {name} , {startTime} , {endTime}".
     *
     * @return String description of task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "E , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + startTime + " , " + endTime + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[E][{X only if complete}] {name} (from: {startTime} to: {endTime})".
     *
     * @return String representation of task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }
}
