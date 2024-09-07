package mortalreminder.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import mortalreminder.errorhandling.MortalReminderException;

// Class and constructor JavaDocs was autocompleted using ChatGPT with minor edits

/**
 * Represents a task that has a deadline. A {@code Deadline} is an object that stores the deadline in
 * {@link LocalDateTime} format to show the date by which the task needs to be completed.
 * <p>
 * The {@code Deadline} class extends the {@code Task} class and implements the {@code TimedTask} interface.
 */
public class Deadline extends Task implements TimedTask {
    private final LocalDateTime deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified description.
     * <p>
     * The description should include the task details followed by the deadline,
     * separated by the "/by" keyword. For example, "Submit report /by 12-09-2024 1700".
     *
     * @param description the description of the task and its deadline.
     * @throws IllegalArgumentException if the description is improperly formatted.
     */
    public Deadline(String description) throws MortalReminderException {
        super(description);
        String[] descriptionString = description.split("/by");
        checkInitialisationDetails(descriptionString);
        this.description = descriptionString[0].trim();
        this.deadline = getTime(descriptionString[1].trim());
        this.type = "D"; // short for Deadline
    }

    // the following constructor and javadoc was created using ChatGPT autocomplete with minor edits

    /**
     * Constructs a new {@code Deadline} task with the specified description, deadline, and completion status.
     * <p>
     * The deadline should be provided as a string in a parsable {@link LocalDateTime} format.
     * This method is only called when loading from a file. This method also assumes the values
     * are always valid as it has already been checked during the adding to file process.
     *
     * @param description the description of the task.
     * @param deadline    the deadline of the task as a {@link String}.
     * @param isDone      whether the task is marked as done.
     */
    public Deadline(String description, String deadline, boolean isDone) throws MortalReminderException {
        super(description);
        this.type = "D";
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline);
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
        String deadlineString = convertTimeToString(this.deadline);
        return this.description + " (by: " + deadlineString + ")";
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
        return this.type + "|" + this.isDone + "|" + this.description + "|" + this.deadline;
    }

    /**
     * Checks for invalid data and parameter numbers passed into the constructor of this class.
     * If there are less or more than 2 objects (description and deadline) in description string, that means
     * there is too much or too little information provided to create the class.
     * As such the ArrayIndexOutOfBoundsException() is thrown to prevent the creation of the file.
     * The getTime() methods help check if the parsing of the dates and times are correct. This function was optimised
     * using chatGPT.
     *
     * @param descriptionString A list of string values passed in from the constructor which are to be checked
     *                          for data validation purposes. An error will be thrown if one of the values are
     *                          invalid and this will stop the creation of the object inside the app.
     * @throws DateTimeParseException The string passed in as the date or time is incorrect and unable to be parsed.
     */
    public void checkInitialisationDetails(String[] descriptionString)
            throws MortalReminderException {
        if (descriptionString.length != 2) {
            throw new MortalReminderException("Please input the correct number of details for deadlines!"
                    + " Remember that you need to include '/by' in the command.");
        }
        try {
            getTime(descriptionString[1].trim());
        } catch (DateTimeParseException e) {
            throw new MortalReminderException("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!");
        }
    }

    /**
     * Retrieves the due date of this task.
     *
     * @return the due date as a {@link LocalDateTime} object.
     */
    @Override
    public LocalDateTime getDueDate() {
        return this.deadline;
    }
}
