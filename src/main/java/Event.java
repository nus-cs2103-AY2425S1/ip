import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    @Expose
    protected String startTimeString;

    @Expose
    protected String endTimeString;

    public Event(
            String description,
            String startTimeString,
            String endTimeString,
            String taskType) throws TaskManagerException {
        super(description, taskType);
        try {
            this.startTime = LocalDateTime.parse(startTimeString.trim(), DateTimeFormatter.ofPattern("yyyy MM dd hh.mma"));
            this.endTime = LocalDateTime.parse(endTimeString.trim(), DateTimeFormatter.ofPattern("yyyy MM dd hh.mma"));
        } catch (DateTimeParseException e) {
            throw new TaskManagerException("Your format for date is wrong! Please use this format: " +
                    "add event {description} " +
                    "/yyyy-MM-dd HHmm (start) /yyyy-MM-dd HHmm (end).",
                    TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT);
        }
        this.startTimeString = startTimeString.trim();
        this.endTimeString = endTimeString.trim();
    }

    @Override
    public String toString() {
        return "[📅 Event] " + super.toString() + " - Mark your calendar! 🗓️ " +
                "(from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) +
                " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) + ")";
    }

    public String getEndTimeString() {
        return this.endTimeString;
    }

    public String getStartTimeString() {
        return this.startTimeString;
    }
}
