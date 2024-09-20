package ned.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ned.Ui;
import ned.exceptions.InvalidTimeFormatException;
import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.MissingTaskFromDateException;
import ned.exceptions.MissingTaskToDateException;
import ned.exceptions.NedException;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It extends the {@code Task} class by adding start and end timings, which are stored as {@code LocalDate} objects.
 *
 * <p>This class provides functionalities to:
 * <ul>
 *   <li>Create a new event task with a description, start date, and end date.</li>
 *   <li>Parse and validate the start and end dates provided in ISO 8601 format (yyyy-MM-dd).</li>
 *   <li>Display the task information along with its timings in a user-friendly format.</li>
 *   <li>Convert the task to a text form suitable for file storage via {@link #toTextForm()}.</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong>
 * <pre>{@code
 * try {
 *     Event eventTask = Event.createEvent("Attend conference", "2023-10-15", "2023-10-17", false);
 *     System.out.println(eventTask);
 * } catch (NedException e) {
 *     System.out.println(e.getMessage());
 * }
 * }</pre>
 *
 * <p><strong>Note:</strong> The start and end dates must follow the ISO 8601 format (yyyy-MM-dd).
 * If invalid dates are provided, a {@code NedException} is thrown with guidance on the correct format.
 *
 * @see Task
 * @see NedException
 */
public class Event extends Task {
    public static final String EVENT_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE = "M'lord, this saved event task has no "
            + "task description!";
    public static final String EVENT_MISSING_FROM_DATE_ERROR_MESSAGE = "M'lord, this saved event task has no "
            + "from date!";
    public static final String EVENT_MISSING_TO_DATE_ERROR_MESSAGE = "M'lord, this saved event task has no to date!";
    private static final String EVENT_INVALID_TIME_FORMAT_ERROR_MESSAGE = "M'lord, the time formatting in /to or /from "
            + "does not follow ISO 8601 (yyyy-mm-dd)"
            + ". Here are examples of valid timings:\n" + Ui.INDENTATIONS + "2015-08-04\n" + Ui.INDENTATIONS
            + "2015-08-04T10:11:30";

    private final LocalDate fromTiming;
    private final LocalDate toTiming;

    /**
     * Constructs a new {@code Event} task with the specified description, start date, end date, and completion status.
     * The dates are parsed from strings in ISO 8601 format (yyyy-MM-dd).
     *
     * @param taskDescription The description of the event task.
     * @param fromTiming The start date of the event as a string in ISO 8601 format.
     * @param toTiming The end date of the event as a string in ISO 8601 format.
     * @param isDone {@code true} if the task is completed; {@code false} otherwise.
     * @throws NedException If the date formats are incorrect.
     */
    private Event(String taskDescription, String fromTiming, String toTiming, boolean isDone) throws NedException {
        super(taskDescription, isDone);
        try {
            this.fromTiming = LocalDate.parse(fromTiming);
            this.toTiming = LocalDate.parse(toTiming);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(EVENT_INVALID_TIME_FORMAT_ERROR_MESSAGE);
        }
        this.taskType = "E";
    }

    /**
     * Creates a new {@code Event} task with the specified description, start date, end date, and completion status.
     * Validates that none of the parameters are blank and that the dates are in the correct format.
     *
     * @param taskDescription The description of the event task.
     * @param eventFromDate The start date of the event in ISO 8601 format (yyyy-MM-dd).
     * @param eventToDate The end date of the event in ISO 8601 format (yyyy-MM-dd).
     * @param isTaskComplete {@code true} if the task is completed; {@code false} otherwise.
     * @return A new {@code Event} object.
     * @throws NedException If any of the parameters are blank.
     */
    public static Event createEvent(String taskDescription, String eventFromDate, String eventToDate,
                                    boolean isTaskComplete) throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException(EVENT_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE);
        } else if (eventFromDate.isBlank()) {
            throw new MissingTaskFromDateException(EVENT_MISSING_FROM_DATE_ERROR_MESSAGE);
        } else if (eventToDate.isBlank()) {
            throw new MissingTaskToDateException(EVENT_MISSING_TO_DATE_ERROR_MESSAGE);
        }
        return new Event(taskDescription, eventFromDate, eventToDate, isTaskComplete);
    }

    /**
     * Creates a new {@code Event} task with the specified description, start date, end date, and completion status.
     * Validates that none of the parameters are blank and that the dates are in the correct format.
     *
     * @param dateTimeObject The LocalDate object, with which to display the formatted time.
     * @return A new String object.
     */
    private String showTiming(LocalDate dateTimeObject) {
        return String.format("%s %d %d", dateTimeObject.getMonth(), dateTimeObject.getDayOfMonth(),
                dateTimeObject.getYear());
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), showTiming(this.fromTiming),
                showTiming(this.toTiming));
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("event|%d|%s|%s|%s", status, this.taskDescription, this.fromTiming, this.toTiming);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == this) {
            return true;
        } else if (!(otherObject instanceof Event)) {
            return false;
        }
        Event typeCastedOtherObject = (Event) otherObject;
        return (isTaskDescriptionEqual(typeCastedOtherObject.taskDescription)
                && isEventFromTimingEqual(typeCastedOtherObject.fromTiming)
                && isEventToTimingEqual(typeCastedOtherObject.toTiming));
    }

    public boolean isTaskDescriptionEqual(String otherTaskDescription) {
        return this.taskDescription.equalsIgnoreCase(otherTaskDescription);
    }

    public boolean isEventFromTimingEqual(LocalDate otherFromTiming) {
        return this.fromTiming.equals(otherFromTiming);
    }

    public boolean isEventToTimingEqual(LocalDate otherToTiming) {
        return this.toTiming.equals(otherToTiming);
    }
}
