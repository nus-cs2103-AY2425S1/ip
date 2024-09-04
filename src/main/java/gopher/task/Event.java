package gopher.task;

import java.time.LocalDateTime;

import gopher.parser.Parser;

/**
 * Represents event task.
 * Include task description, start date and end date.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of Event class
     *
     * @param name name of task
     * @param startDate start date of the task
     * @param endDate end date of the task
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = Parser.parseDateString(startDate);
        this.endDate = Parser.parseDateString(endDate);
    }

    @Override
    public String getSaveMessage() {
        return String.format("E | %s | %s | %s | %s",
                getStatusIcon(),
                this.name,
                Parser.parseLocalDateTime(this.startDate),
                Parser.parseLocalDateTime(this.endDate));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)",
                super.toString(),
                Parser.displayDate(this.startDate),
                Parser.displayDate(this.endDate));
    }
}
