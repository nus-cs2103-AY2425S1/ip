package luffy;

/**
 * Represents a task that has a start time and an end time
 */
public class Event extends Task {

    private final String start;
    private final String end;

    /**
     * Constructor to create an eventTask
     * @param taskInfo task that the user wants to store
     * @param start start time of the given task
     * @param end end time of the given task
     * @param isDone state of task
     */
    public Event(String taskInfo, String start, String end, boolean isDone) {
        super(taskInfo, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[E][X] %s (from: %s to: %s)", super.getTaskInfo(), this.start, this.end);
        } else {
            return String.format("[E][ ] %s (from: %s to: %s)", super.getTaskInfo(), this.start, this.end);
        }
    }

    @Override
    public String dataToSave() {
        if (checkIsDone()) {
            return String.format("EVENT         | 1 | %s | START: %s | END: %s",
                    super.getTaskInfo(), this.start, this.end);
        } else {
            return String.format("EVENT         | 0 | %s | START: %s | END: %s",
                    super.getTaskInfo(), this.start, this.end);
        }
    }
}
