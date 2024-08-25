import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime endTime;

    @Expose
    protected String endTimeString;

    public Deadline(String description, String endTimeString, String taskType) throws TaskManagerException {
        super(description, taskType);
        try {
            this.endTime = LocalDateTime.parse(endTimeString.trim(), DateTimeFormatter.ofPattern("yyyy MM dd hh.mma"));
        } catch (DateTimeParseException e) {
            throw new TaskManagerException("Your format for date is wrong! Please use this format: add deadline {description}" +
                    "/yyyy MM dd hh.mma (e.g. add deadline homework /2024 11 17 10.00AM)",
                    TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT);
        }
        this.endTimeString = endTimeString.trim();
    }

    @Override
    public String toString() {
        return "[⏰ Deadline] " + super.toString() + " - Don't miss it! ⏳ (due: " +
                this.endTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mma")) + ")";
    }

    public String getEndTimeString() {
        return this.endTimeString;
    }
}

