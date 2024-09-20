package seedu.avo.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.avo.utils.DateTime;

/**
 * Represents a task with start and end times
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * @param name The name of task
     * @param startTime The start datetime of the task
     * @param endTime The end datetime of the task
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        boolean isOnStartTime = date.equals(startDate);
        boolean isOnEndTime = date.equals(endDate);
        boolean isBetweenStartTimeAndEndTime = date.isAfter(startDate) && date.isBefore(endDate);
        return isOnStartTime || isOnEndTime || isBetweenStartTimeAndEndTime;
    }
    @Override
    public String formatData() {
        String startTimeStr = DateTime.format(startTime);
        String endTimeStr = DateTime.format(endTime);
        return String.format("E : %s : %s : %s", super.formatData(), startTimeStr, endTimeStr);
    }
    @Override
    public String toString() {
        String startTimeStr = DateTime.format(startTime);
        String endTimeStr = DateTime.format(endTime);
        return String.format("[E] %s (from: %s to: %s)", super.toString(), startTimeStr, endTimeStr);
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event e) {
            return super.equals(o)
                    && startTime.equals(e.startTime)
                    && endTime.equals(e.endTime);
        }
        return false;
    }
}
