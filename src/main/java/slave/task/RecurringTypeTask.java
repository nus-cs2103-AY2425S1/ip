package slave.task;

import java.time.LocalDate;

/**
 * RecurringTypeTask objects are tasks that can be a recurring event.
 */
public abstract class RecurringTypeTask extends Task {
    private RecurringType recurringType;

    public enum RecurringType {
        NEVER, DAILY, WEEKLY, BIMONTHLY, MONTHLY, QUARTERLY, BIANNUALLY, ANNUALLY
    }

    /**
     * Creates a RecurringTypeTask of the following specifications.
     *
     * @param taskName
     * @param recurringType
     */
    public RecurringTypeTask(String taskName, RecurringType recurringType) {
        super(taskName);
        this.recurringType = recurringType;
    }

    /**
     * Creates a RecurringTypeTask of the following specifications.
     *
     * @param taskName
     * @param recurringType
     */
    public RecurringTypeTask(String taskName, boolean isCompleted, RecurringType recurringType) {
        super(taskName, isCompleted);
        this.recurringType = recurringType;
    }

    /**
     * @return whether the task is a recurring task
     */
    public boolean isRecurring() {
        return recurringType != RecurringType.NEVER;
    }

    /**
     * @return how often the type reoccurs
     */
    public RecurringType getRecurringType() {
        return this.recurringType;
    }

    /**
     * Sets the task as a non-recurring task.
     *
     * @return the task itself to enable method chaining.
     */
    public Task setAsNotRecurring() {
        this.recurringType = RecurringType.NEVER;
        return this;
    }

    /**
     * Sets the task as a recurring task.
     *
     * @return the task itself to enable method chaining.
     */
    public Task setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
        return this;
    }

    /**
     * Prints the task in the required format.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isCompleted()) {
            sb.append("[X]");
        } else {
            sb.append("[]");
        }
        String recurringStatus = recurringType != RecurringType.NEVER ? "[rec] " : " ";
        sb.append(recurringStatus);
        sb.append(getTask());
        return sb.toString();
    }

    public abstract String saveFormat();

    public abstract boolean hasEnded(LocalDate date);

    public abstract RecurringTypeTask createRecurringEvent();
}
