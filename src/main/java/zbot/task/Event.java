package zbot.task;

import java.time.LocalDateTime;
import zbot.Parser;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.startDate = from;
        this.endDate = to;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.formatDateTimeToOutput(startDate), Parser.formatDateTimeToOutput(endDate));
    }
}
