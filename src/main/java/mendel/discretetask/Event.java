package mendel.discretetask;

import java.time.LocalDate;

import mendel.datetime.DateTimeManager;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

/**
 * Represents an event task.
 * The Event class extends the Task class and includes additional attributes
 * to handle the description, start time, and end time of the task.
 */
public class Event extends Task {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start date of the event task of type string.
     * @param to The end time of the event task of type string.
     */
    private Event(String description, String from, String to) {
        super(String.format("%s (from: %s to %s)", description, from, to));
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Factory method to create a new Event object by parsing
     * The raw input for task description, start date, and end date.
     *
     * @param rawDescription The raw description string containing the event description, start date, and end date.
     * @return A new Event object with the parsed description, start date, and end date.
     */
    public static Event of(String rawDescription) {
        String[] description = parseDescription(rawDescription);
        assert description.length == 3 : "Description has wrong format";
        return new Event(description[0], description[1], description[2]);
    }

    /**
     * Factory method to load a Event object from stored data.
     *
     * @param mark A boolean indicating whether the database task was marked as done.
     * @param description The database description of the event.
     * @param from The database start date of the event in a formatted string.
     * @param to The database end date of the event in a formatted string.
     * @return An Event object initialized with the provided parameters.
     */
    public static Event loadOf(boolean mark, String description, String from, String to) {
        Event initObj = new Event(description, from, to);
        if (mark) {
            initObj.markAsDone();
        } else {
            initObj.markAsUnDone();
        }
        return initObj;
    }

    /**
     * Parses the raw description string to extract the event description, start date, and end date.
     *
     * @param rawDescription The raw description string containing the event description, start date, and end date.
     * @return An array where the first element is the event description, the second element is the start date,
     *         and the third element is the end date.
     * @throws MendelException if there are issues with the format or content of the raw description.
     */
    private static String[] parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] slashSegments = rawDescription.split(" /from ");
        String[] mainMessage = slashSegments[0].split(" ");
        assert slashSegments.length > 1 : "Description has wrong format";
        String startMsg = new DateTimeManager(slashSegments[1].split(" /to ")[0]).toString();
        String endMsg = new DateTimeManager(slashSegments[1].split(" /to ")[1]).toString();
        String reformattedMsg = parseArrayToFullString(mainMessage);
        return new String[]{reformattedMsg, startMsg, endMsg};
    }

    private static String parseArrayToFullString(String[] mainMessage) {
        String reformattedMsg = "";
        for (int i = 1; i < mainMessage.length; i++) {
            reformattedMsg += mainMessage[i] + " ";
        }
        return reformattedMsg.strip();
    }

    /**
     * Validates the raw description string and handles various error conditions.
     *
     * @param rawDescription The raw description string to be validated.
     * @throws MendelException if there are issues such as missing description,
     *                         missing start or end date, or incorrect formatting.
     */
    private static void handleError(String rawDescription) throws MendelException {
        String[] slashSegments = rawDescription.split(" /from ");
        handleGeneralFormattingError(slashSegments, rawDescription);
        String[] dateTimeMsg = slashSegments[1].split(" /to ");
        handleDateTimeError(dateTimeMsg);
    }

    private static void handleGeneralFormattingError(String[] slashSegments, String rawDescription)
            throws MendelException {
        String[] mainMessage = slashSegments[0].split(" ");
        ConditionalExceptionHandler.of()
                .orConditionTriggerException(slashSegments.length < 2)
                .andConditionTriggerException(slashSegments[0].equals("event"),
                        "OOPS! event needs more details.\nAdd description.")
                .andConditionTriggerException(rawDescription.split("/from").length != slashSegments.length,
                        "OOPS! deadline from wrongly formatted.\nPlease add spaces around /from.")
                .conditionTriggerException(slashSegments.length < 2,
                        "OOPS! I am unsure of start.\nPlease specify only one start.")
                .conditionTriggerException(slashSegments.length > 2,
                        "OOPS! event start cannot be empty.\nPlease indicate a start.")
                .conditionTriggerException(mainMessage.length == 1,
                        "OOPS! event description cannot be empty.\nAdd description.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length != slashSegments[1]
                                .split("/to").length,
                        "OOPS! deadline to wrongly formatted.\nPlease add spaces around /to.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length < 2,
                        "OOPS! I am unsure of end.\nPlease specify an end.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length > 2,
                        "OOPS! I am unsure of end.\nPlease specify only one end.");
    }

    private static void handleDateTimeError(String[] dateTimeMsg) throws MendelException {
        String startMsg = dateTimeMsg[0];
        String endMsg = dateTimeMsg[1];
        ConditionalExceptionHandler.of()
                .conditionTriggerException(new DateTimeManager(startMsg)
                                .isEarlierThan(new DateTimeManager(LocalDate.now().toString())),
                        "OOPS! Start day is earlier than today.\nPlease ensure valid time period.")
                .conditionTriggerException(new DateTimeManager(endMsg)
                                .isEarlierThan(new DateTimeManager(LocalDate.now().toString())),
                        "OOPS! End day is earlier than today.\nPlease ensure valid time period.")
                .conditionTriggerException(new DateTimeManager(endMsg).isEarlierThan(new DateTimeManager(startMsg)),
                        "OOPS! Start day is later than end day.\nPlease ensure valid time period.")
                .conditionTriggerException(startMsg.isEmpty() && endMsg.isEmpty(),
                        "OOPS! I am unsure of start and due.\nPlease specify a start and due.")
                .conditionTriggerException(startMsg.isEmpty(), "OOPS! I am unsure of start.\nPlease specify a start.")
                .conditionTriggerException(endMsg.isEmpty(), "OOPS! I am unsure of due.\nPlease specify a due.");
    }

    /**
     * Checks if the given date matches the due date of this Event task.
     *
     * @param formattedDate The date to be compared in a formatted string.
     * @return true if the formattedDate matches the task's due date, ignoring the time component; false otherwise.
     */
    @Override
    public boolean isTargetDueDate(String formattedDate) {
        return new DateTimeManager(formattedDate).removeTimeStamp()
                .equals(new DateTimeManager(this.to).removeTimeStamp());
    }

    /**
     * Checks if Event deadline is before the date specified and is after today
     *
     * @param formattedDate date to look back from
     * @return true if Event deadline is before the date specified and is after today contains pattern, false otherwise
     */
    @Override
    public boolean isIncompleteWithinTargetDueDate(String formattedDate) {
        DateTimeManager inputDate = new DateTimeManager(formattedDate);
        DateTimeManager toDate = new DateTimeManager(this.to);
        DateTimeManager fromDate = new DateTimeManager(this.from);
        long timeDeadline = new DateTimeManager(inputDate.removeTimeStamp()).toEpochTime();
        long timeTo = new DateTimeManager(toDate.removeTimeStamp()).toEpochTime();
        long timeFrom = new DateTimeManager(fromDate.removeTimeStamp()).toEpochTime();
        long today = new DateTimeManager(LocalDate.now().toString()).toEpochTime();
        boolean isTaskInRange = (timeDeadline > timeTo || timeDeadline > timeFrom)
                && (timeTo > today || timeFrom > today);
        return isTaskInRange && !super.getStatus();
    }

    /**
     * Checks description contains input pattern specified
     *
     * @param matchingString  pattern to look for
     * @return true if description contains pattern, false otherwise
     */
    @Override
    public boolean isMatchingDescription(String matchingString) {
        return this.description.contains(matchingString);
    }

    /**
     * Parses the details of this Event task into a string format suitable for storage in a database.
     *
     * @return A string containing the task type, status, description, start date, and end date.
     */
    @Override
    public String parseDetailsForDB() {
        return String.format("E | %d | %s | %s | %s", super.getStatus() ? 1 : 0, this.description, this.from, this.to);
    }

    /**
     * Returns a string representation of the Event task, including its type and status.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
