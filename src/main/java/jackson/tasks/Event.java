package jackson.tasks;

import jackson.utils.CustomDateTime;

/**
 * Event class containing name, from date and to date.
 */
public class Event extends Task {
    private CustomDateTime from;
    private CustomDateTime to;

    /**
     * Constructs Event Task instance.
     * @param name String name of the task.
     * @param from String start date of the task.
     * @param to String end date of the task.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = new CustomDateTime(from);
        this.to = new CustomDateTime(to);
    }

    @Override
    public CustomDateTime getStartDateTime() {
        return this.from;
    }

    @Override
    public CustomDateTime getEndDateTime() {
        return this.to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s | to: %s)",
                this.getTaskType(), super.toString(), this.from, this.to);
    }
}
