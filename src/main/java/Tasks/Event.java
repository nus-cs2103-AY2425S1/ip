package Tasks;

import Exceptions.NedException;
import Default.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    private LocalDate fromTiming;
    private LocalDate toTiming;

    private Event(String taskDescription, String fromTiming, String toTiming, boolean isDone) throws NedException {
        super(taskDescription, isDone);
        try {
            this.fromTiming = LocalDate.parse(fromTiming);
            this.toTiming = LocalDate.parse(toTiming);
        } catch (DateTimeParseException e) {
            throw new NedException("M'lord, the time formatting in /to or /from does not follow ISO 8601 (yyyy-mm-dd)" +
                    ". Here are " +
                    "examples" +
                    " of valid " +
                    "timings:\n" + Ui.INDENTATIONS + "2015-08-04\n" + Ui.INDENTATIONS + "2015-08-04T10:11:30");
        }
        this.taskType = "E";
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

    public static Event createEvent(String taskDescription, String eventFromDate, String eventToDate,
                                    boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved event task has no task description!");
        } else if (eventFromDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no from date!");
        } else if (eventToDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no to date!");
        }
        return new Event(taskDescription, eventFromDate, eventToDate, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("event|%d|%s|%s|%s", status,this.taskDescription, this.fromTiming, this.toTiming);
    }

}
