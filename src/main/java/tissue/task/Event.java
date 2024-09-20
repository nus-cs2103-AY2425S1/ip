package tissue.task;

/** Wrapper for an event task. */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for accepting isDone of type boolean.
     *
     * @param isDone Boolean to store if a task is done.
     * @param task Name of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public Event(boolean isDone, String task, String from, String to) {
        super(isDone, task);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for accepting isDone of type int.
     *
     * @param isDone Int to store if a task is done.
     * @param task Name of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public Event(int isDone, String task, String from, String to) {
        super(isDone == 1, task);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        String task = super.getTask() + " " + String.format("(from: %s to: %s)", from, to);
        return super.getIsDone() ? "[E][X] " + task : "[E][ ] " + task;
    }
}
