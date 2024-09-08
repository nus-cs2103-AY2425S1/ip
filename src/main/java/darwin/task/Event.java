package darwin.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private String symbol = "E";
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Initialises a Event task with name, start and end.
     * @param name A string of the task.Task's name.
     * @param start A string indicating the task.Task's start date / time.
     * @param end A string indicating the task.Task's end date / time.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + String.format(" (from: %s to: %s)", super.formatDate(this.start), super.formatDate(this.end));
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + this.start + "," + this.end;
    }
}
