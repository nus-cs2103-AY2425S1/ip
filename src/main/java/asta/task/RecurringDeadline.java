package asta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a recurring deadline task. The task has a specific deadline and repeats at a specified interval.
 * Example Command: repeat deadline submit report /by 12/09/2024 1800 /weekly
 */
public class RecurringDeadline extends Deadline {
    private final int recurrenceInterval; // Interval in days
    private boolean isNextOccurrenceGenerated; // Tracks if the next occurrence has been generated

    /**
     * Constructs a RecurringDeadline task with a specified description, deadline, and recurrence interval. The task is
     * marked as not having generated its next occurrence by default.
     *
     * @param description        The description of the task.
     * @param byDateTime         The deadline by which the task should be completed.
     * @param recurrenceInterval The interval in days for the task to recur.
     */
    public RecurringDeadline(String description, LocalDateTime byDateTime, int recurrenceInterval) {
        super(description, byDateTime);
        this.recurrenceInterval = recurrenceInterval;
        this.isNextOccurrenceGenerated = false;
    }

    /**
     * Constructs a RecurringDeadline task with a specified description, deadline, recurrence interval, and a flag to
     * indicate whether the next occurrence has already been generated.
     *
     * @param description               The description of the task.
     * @param byDateTime                The deadline by which the task should be completed.
     * @param recurrenceInterval        The interval in days for the task to recur.
     * @param isNextOccurrenceGenerated A boolean flag indicating whether the next occurrence has already been
     *                                  generated.
     */
    public RecurringDeadline(String description, LocalDateTime byDateTime, int recurrenceInterval,
                             boolean isNextOccurrenceGenerated) {
        super(description, byDateTime);
        this.recurrenceInterval = recurrenceInterval;
        this.isNextOccurrenceGenerated = isNextOccurrenceGenerated;
    }

    /**
     * Generates the next occurrence of the recurring task, if there are more occurrences remaining.
     *
     * @return The next RecurringDeadline if applicable, or null if no more occurrences.
     */
    public RecurringDeadline generateNextOccurrence() {
        if (!isNextOccurrenceGenerated) {
            LocalDateTime nextDateTime = by.plusDays(recurrenceInterval);
            RecurringDeadline nextRecurringTask = new RecurringDeadline(description, nextDateTime, recurrenceInterval);
            isNextOccurrenceGenerated = true; // Mark that the next occurrence has been generated
            return nextRecurringTask;
        }
        return null; // No more occurrences or already generated
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "RD | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter) + " | "
            + recurrenceInterval + " | " + isNextOccurrenceGenerated;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[RD]" + super.getStatusIcon() + description + " (by: " + by.format(formatter) + ")" + " (every "
            + recurrenceInterval + " days)";
    }

    public boolean isNextOccurrenceGenerated() {
        return isNextOccurrenceGenerated;
    }
}
