package ned.tasks;

import ned.Ui;
import ned.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public static Event createEvent(String taskDescription, String eventFromDate, String eventToDate,
                                    boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException(EVENT_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE);
        } else if (eventFromDate.isBlank()) {
            throw new MissingTaskFromDateException(EVENT_MISSING_FROM_DATE_ERROR_MESSAGE);
        } else if (eventToDate.isBlank()) {
            throw new MissingTaskToDateException(EVENT_MISSING_TO_DATE_ERROR_MESSAGE);
        }
        return new Event(taskDescription, eventFromDate, eventToDate, taskStatus);
    }

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

}
