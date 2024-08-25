package mortal_reminder.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

// Class and constructor JavaDocs was autocompleted using ChatGPT with minor edits
/**
 * Represents a task that spans a period of time. It stores the start and end time of the event using
 * {@link LocalDateTime} format to show the duration of the event and when it will be held.
 * <p>
 * The {@code Events} class extends the {@code Task} class and implements the {@code TimedTask} interface.
 */
public class Events extends Task implements TimedTask {
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    /**
     * Constructs a new {@code Events} task with the specified description.
     * <p>
     * The description should include the task details followed by the start and end times,
     * separated by the "/from" and "/to" keywords. For example, "Team meeting /from 12-09-2024 1000 /to 12-09-2024 1100".
     *
     * @param description the description of the event, including start and end times.
     * @throws IllegalArgumentException if the description is improperly formatted.
     */
    public Events(String description) {
        super(description);
        String[] descriptionString = description.split("/from|/to");
        checkInitialisationDetails(descriptionString);
        this.description = descriptionString[0];
        this.fromTime = getTime(descriptionString[1].trim());
        this.toTime = getTime(descriptionString[2].trim());
        this.type = "E";
    }

    // the following constructor and javadoc was created using ChatGPT autocomplete with minor edits
    /**
     * Constructs a new {@code Events} task with the specified description, start time, end time, and completion status.
     * <p>
     * The start and end times should be provided as strings in a parsable {@link LocalDateTime} format.
     * This method is only called when loading from a file. This method also assumes the values
     * are always valid as it has already been checked during the adding to file process.
     *
     * @param description the description of the event.
     * @param fromTime    the start time of the event as a {@link String}.
     * @param toTime      the end time of the event as a {@link String}.
     * @param isDone      whether the event is marked as done.
     */
    public Events(String description, String fromTime, String toTime, boolean isDone) {
        super(description);
        this.type = "E";
        this.description = description;
        this.fromTime = LocalDateTime.parse(fromTime);
        this.toTime = LocalDateTime.parse(toTime);
        this.isDone = isDone;
    }

    /**
     * Method to get the description factor of the task.
     * The description is not always the same as the description value passed into the this.description variable
     * especially for the deadline and event classes. This method assumes that the variables have been properly
     * initialised in the constructor.
     *
     * @return string of the description after processing.
     */
    @Override
    public String getDescription() {
        String fromTimeString = convertTimeToString(fromTime);
        String toTimeString = convertTimeToString(toTime);
        return this.description
                + " (from: " + fromTimeString + ", to: " + toTimeString + ")";
    }

    /**
     * Converts the class into a string to be added to the storage file.
     * Converts all the variables from their respective object types to a string format which can be stored
     * inside the storage file and later be recognised by the parser to convert back into a task type.
     *
     * @return string of object summary to be placed into the storage text file.
     */
    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description + "|" + this.fromTime + "|" + this.toTime;
    }

    /**
     * Checks for invalid data and parameter numbers passed into the constructor of this class.
     * If there are less or more than 3 objects in description string, that means there is too much
     * or too little information provided to create the class. As such the ArrayIndexOutOfBoundsException()
     * is thrown to prevent the creation of the file. The getTime() methods help check if the parsing of the
     * dates and times are correct.
     *
     *
     * @param descriptionString A list of string values passed in from the constructor which are to be checked
     *                          for data validation purposes. An error will be thrown if one of the values are
     *                          invalid and this will stop the creation of the object inside the app.
     * @throws DateTimeParseException The string passed in as the date or time is incorrect and unable to be parsed.
     */
    // the following function was optimised using chatGPT
    public void checkInitialisationDetails(String[] descriptionString)
            throws DateTimeParseException {
        if (descriptionString.length != 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        getTime(descriptionString[1].trim());
        getTime(descriptionString[2].trim());
    }

    /**
     * Retrieves the due date of this task.
     *
     * @return the due date as a {@link LocalDateTime} object.
     */
    @Override
    public LocalDateTime getDueDate() {
        return this.fromTime;
    }
}
