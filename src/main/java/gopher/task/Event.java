package gopher.task;

import java.time.LocalDateTime;

import gopher.exception.InvalidDurationException;
import gopher.exception.InvalidTokenException;
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
    public void update(String[] tokens) throws InvalidTokenException,
            InvalidDurationException {
        String[] parsedResult = Parser.parseUpdateEventTaskCommand(tokens);

        String taskName = parsedResult[0];
        String startDateString = parsedResult[1];
        String endDateString = parsedResult[2];

        if (!taskName.isEmpty()) {
            this.name = taskName;
        }

        if (!startDateString.isEmpty() || !endDateString.isEmpty()) {
            LocalDateTime newStartDate = startDateString.isEmpty()
                    ? this.startDate
                    : Parser.parseDateString(startDateString);
            LocalDateTime newEndDate = endDateString.isEmpty()
                    ? this.endDate
                    : Parser.parseDateString(endDateString);
            if (newStartDate.isBefore(newEndDate)) {
                this.startDate = newStartDate;
                this.endDate = newEndDate;
            } else {
                throw new InvalidDurationException();
            }
        }
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
