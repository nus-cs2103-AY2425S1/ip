package mortal_reminder.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements TimedTask {

    private final LocalDateTime deadline;

    public Deadline(String description) {
        super(description);
        String[] descriptionString = description.split("/by");
        checkInitialisationDetails(descriptionString);
        this.description = descriptionString[0].trim();
        this.deadline = getTime(descriptionString[1].trim());
        this.type = "D";

    }

    // the following constructor was created using ChatGPT autocomplete
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.type = "D";
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline);
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        String deadlineString = convertTimeToString(this.deadline);
        return this.description + " (by: " + deadlineString + ")";
    }

    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description + "|" + this.deadline;
    }

    // the following function was optimised using chatGPT
    public void checkInitialisationDetails(String[] descriptionString)
            throws DateTimeParseException {
        if (descriptionString.length > 2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        getTime(descriptionString[1].trim());
    }

    @Override
    public LocalDateTime getDueDate() {
        return this.deadline;
    }
}
