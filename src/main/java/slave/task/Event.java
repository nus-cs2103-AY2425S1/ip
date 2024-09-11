package slave.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import slave.InvalidChronologicalOrderException;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;

/**
 * An Event object is a Task with a start and end date.
 */
public class Event extends RecurringTypeTask {
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates an event with the name taskName, starting at start and ending at end.
     * An InvalidChronologicalOrderException is thrown if the start date is after the end date.
     *
     * @param taskName
     * @param start
     * @param end
     * @throws InvalidChronologicalOrderException
     */
    public Event(String taskName, RecurringType recurringType, LocalDate start, LocalDate end)
            throws InvalidChronologicalOrderException {
        super(taskName, recurringType);
        if (start.isAfter(end)) {
            throw new InvalidChronologicalOrderException("Event cannot end before it starts");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event with the specified parameters.
     * An InvalidChronologicalOrderException is thrown if the start date is after the end date.
     *
     * @param taskName
     * @param isCompleted
     * @param recurringType
     * @param start
     * @param end
     * @throws InvalidChronologicalOrderException
     */
    public Event(String taskName, boolean isCompleted, RecurringType recurringType, LocalDate start, LocalDate end)
            throws InvalidChronologicalOrderException {
        super(taskName, isCompleted, recurringType);
        if (start.isAfter(end)) {
            throw new InvalidChronologicalOrderException("Event cannot end before it starts");
        }
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawStart() {
        return this.start;
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawEnd() {
        return this.end;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }

    /**
     * Formatted as such:
     * (Task type),(isCompleted),(isRecurring),(Task name),(startDate),(endDate)
     *
     * @return the string representation of the task to be written into the save file.
     */
    @Override
    public String saveFormat() {
        return "event," + isCompleted() + "," + getRecurringType() + "," + getTask() + "," + start + "," + end;
    }

    /**
     * Checks if the event has ended on the specified date.
     *
     * @param date
     * @return a boolean indicating if the event has ended on the specified date.
     */
    @Override
    public boolean hasEnded(LocalDate date) {
        return date.isAfter(this.end);
    }

    /**
     * Creates a new Event starting after the previous event ended, according to how frequently it occurs
     */
    @Override
    public RecurringTypeTask createRecurringEvent() {
        LocalDate today = LocalDate.now();
        int duration = end.compareTo(start);
        LocalDate newStart = start;
        LocalDate newEnd = end;
        switch (getRecurringType()) {
        case DAILY:
            newStart = today.plusDays(1);
            break;
        case WEEKLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusWeeks(1);
            }
            break;
        case BIMONTHLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusWeeks(2);
            }
            break;
        case MONTHLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusMonths(1);
            }
            break;
        case QUARTERLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusMonths(3);
            }
            break;
        case BIANNUALLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusMonths(6);
            }
            break;
        case ANNUALLY:
            while (today.isAfter(newStart)) {
                newStart = newStart.plusYears(1);
            }
        default:
            // do nothing
            // code should not reach here as the load() function cannot allocate an undefined recurrence type
        }
        newEnd = newStart.plusDays(duration);

        try {
            return new Event(getTask(), false, getRecurringType(), newStart, newEnd);
        } catch (InvalidChronologicalOrderException e) {
            return null;
        }
    }
}
