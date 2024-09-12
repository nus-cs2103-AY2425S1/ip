package asta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a recurring deadline task. The task has a specific deadline and repeats at a specified interval.
 * Example Command: repeat deadline submit report /by 12/09/2024 1800 /weekly
 */
public class RecurringDeadline extends Deadline {
    private final int recurrenceInterval; // Interval in days
    private boolean nextOccurrenceGenerated; // Tracks if the next occurrence has been generated

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
        this.nextOccurrenceGenerated = false;
    }

    /**
     * Constructs a RecurringDeadline task with a specified description, deadline, recurrence interval, and a flag to
     * indicate whether the next occurrence has already been generated.
     *
     * @param description             The description of the task.
     * @param byDateTime              The deadline by which the task should be completed.
     * @param recurrenceInterval      The interval in days for the task to recur.
     * @param nextOccurrenceGenerated A boolean flag indicating whether the next occurrence has already been generated.
     */
    public RecurringDeadline(String description, LocalDateTime byDateTime, int recurrenceInterval,
                             boolean nextOccurrenceGenerated) {
        super(description, byDateTime);
        this.recurrenceInterval = recurrenceInterval;
        this.nextOccurrenceGenerated = nextOccurrenceGenerated;
    }

    public boolean isNextOccurrenceGenerated() {
        return nextOccurrenceGenerated;
    }

    /**
     * Generates the next occurrence of the recurring task, if there are more occurrences remaining.
     *
     * @return The next RecurringDeadline if applicable, or null if no more occurrences.
     */
    public RecurringDeadline generateNextOccurrence() {
        if (!nextOccurrenceGenerated) {
            LocalDateTime nextDateTime = by.plusDays(recurrenceInterval);
            RecurringDeadline nextRecurringTask = new RecurringDeadline(description, nextDateTime, recurrenceInterval);
            nextOccurrenceGenerated = true; // Mark that the next occurrence has been generated
            return nextRecurringTask;
        }
        return null; // No more occurrences or already generated
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "RD | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter) + " | "
                + recurrenceInterval + " | " + nextOccurrenceGenerated;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[RD]" + super.getStatusIcon() + description + " (by: " + by.format(formatter) + ")" + " (every "
                + recurrenceInterval + " days)";
    }
}
