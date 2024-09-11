package slave.task;

import slave.InvalidChronologicalOrderException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * a Deadline object is a Task with a deadline.
 */
public class Deadline extends RecurringTypeTask {
    private LocalDate deadline;

    /**
     * Creates an incomplete, non-recurring Deadline object with the specified parameters.
     *
     * @param taskName is the task name.
     * @param deadline is the deadline of the task.
     */
    public Deadline(String taskName, RecurringType recurringType, LocalDate deadline) {
        super(taskName, recurringType);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline object with the specified parameters.
     *
     * @param isCompleted is the state of completeness fo the task.
     * @param taskName    is the task name.
     * @param deadline    is the deadline of the task.
     */
    public Deadline(String taskName, boolean isCompleted, RecurringType recurringType, LocalDate deadline) {
        super(taskName, isCompleted, recurringType);
        this.deadline = deadline;
    }

    public String getFormattedDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }

    /**
     * Formatted as such:
     * (Task type),(isCompleted),(isRecurring),(Task name),(Deadline)
     *
     * @return the string representation of the task to be written into the save file.
     */
    @Override
    public String saveFormat() {
        return "deadline," + isCompleted() + "," + getRecurringType() + "," + getTask() + "," + deadline;
    }

    /**
     * Checks if the deadline has ended (i.e. is past) on the specified date.
     *
     * @param date
     * @return a boolean indicating if the deadline has ended on the specified date.
     */
    @Override
    public boolean hasEnded(LocalDate date) {
        return date.isAfter(this.deadline);
    }

    /**
     * Creates a new Deadline starting after the previous deadline ended, according to how frequently it occurs
     */
    @Override
    public RecurringTypeTask createRecurringEvent() {
        LocalDate today = LocalDate.now();
        LocalDate newDeadline = deadline;
        switch (getRecurringType()) {
        case DAILY:
            newDeadline = today.plusDays(1);
            break;
        case WEEKLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusWeeks(1);
            }
            break;
        case BIMONTHLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusWeeks(2);
            }
            break;
        case MONTHLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusMonths(1);
            }
            break;
        case QUARTERLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusMonths(3);
            }
            break;
        case BIANNUALLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusMonths(6);
            }
            break;
        case ANNUALLY:
            while (today.isAfter(newDeadline)) {
                newDeadline = newDeadline.plusYears(1);
            }
        default:
            // do nothing
            // code should not reach here as the load() function cannot allocate an undefined recurrence type
        }

        return new Deadline(getTask(), false, getRecurringType(), newDeadline);
    }
}
