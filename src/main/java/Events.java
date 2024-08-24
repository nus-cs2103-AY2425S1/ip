import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task implements TimedTask {
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public Events(String description) {
        super(description);
        String[] descriptionString = description.split("/from|/to");
        checkInitialisationDetails(descriptionString);
        this.description = descriptionString[0];
        this.fromTime = getTime(descriptionString[1].trim());
        this.toTime = getTime(descriptionString[2].trim());
        this.type = "E";
    }

    public Events(String description, String fromTime, String toTime, boolean isDone) {
        super(description);
        this.type = "E";
        this.description = description;
        this.fromTime = LocalDateTime.parse(fromTime);
        this.toTime = LocalDateTime.parse(toTime);
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        String fromTimeString = convertTimeToString(fromTime);
        String toTimeString = convertTimeToString(toTime);
        return this.description
                + " (from: " + fromTimeString + ", to: " + toTimeString + ")";
    }

    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description + "|" + this.fromTime + "|" + this.toTime;
    }

    // the following function was optimised using chatGPT
    public void checkInitialisationDetails(String[] descriptionString)
            throws DateTimeParseException {
        if (descriptionString.length > 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        getTime(descriptionString[1].trim());
        getTime(descriptionString[2].trim());
    }

    @Override
    public LocalDateTime getDueDate() {
        return this.fromTime;
    }
}
