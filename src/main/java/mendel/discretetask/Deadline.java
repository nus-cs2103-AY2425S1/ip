package mendel.discretetask;

import mendel.datetime.DateTimeManager;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

/**
 * Represents deadline task. The Deadline class extends the Task class
 * and includes additional attributes to handle the description and the due date.
 */
public class Deadline extends Task {
    private final String description;
    private final String by;

    /**
     * Constructs a Deadline object with description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task of type string.
     */
    private Deadline(String description, String by) {
        super(String.format("%s (by: %s)", description, by));
        this.description = description;
        this.by = by;
    }

    /**
     * Factory method to create a new Deadline object by parsing
     * raw input for task description and the due date.
     *
     * @param rawDescription The raw description string containing the task description and due date.
     * @return A new Deadline object with the parsed description and due date.
     */
    public static Deadline of(String rawDescription) {
        String[] descriptionLst = parseDescription(rawDescription);
        assert descriptionLst.length == 2 : "Description has wrong format";
        return new Deadline(descriptionLst[0], descriptionLst[1]);
    }

    /**
     * Factory method to load a Deadline object from stored data.
     *
     * @param mark A boolean indicating whether the database task was marked as done.
     * @param description The database description of the task.
     * @param by The database due date of the task.
     * @return A Deadline object initialized with the provided parameters.
     */
    public static Deadline loadOf(boolean mark, String description, String by) {
        Deadline initObj = new Deadline(description, by);
        if (mark) {
            initObj.markAsDone();
        } else {
            initObj.markAsUnDone();
        }
        return initObj;
    }

    /**
     * Parses the raw description string to extract the task description and the due date.
     *
     * @param rawDescription The raw description string containing the task description and due date.
     * @return A String array where the first element is the task description and the second element is the due date.
     * @throws MendelException if there are issues with the format or content of the raw description.
     */
    private static String[] parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] slashSegments = rawDescription.split(" /by ");
        assert slashSegments.length > 1 : "Description has wrong format";
        String[] mainMessage = slashSegments[0].split(" ");
        String endMsg = new DateTimeManager(slashSegments[1]).toString();
        String reformattedMsg = parseArrayToFullString(mainMessage);
        return new String[]{reformattedMsg, endMsg};
    }

    private static String parseArrayToFullString(String[] mainMessage) {
        String reformattedMsg = "";
        for (int i = 1; i < mainMessage.length; i++) {
            if (i == mainMessage.length - 1) {
                reformattedMsg += mainMessage[i];
            } else {
                reformattedMsg += mainMessage[i] + " ";
            }
        }
        return reformattedMsg;
    }

    /**
     * Validates the raw description string and handles various error conditions.
     *
     * @param rawDescription The raw description string to be validated.
     * @throws MendelException if there are issues such as missing description missing due date, or incorrect formatting
     */
    private static void handleError(String rawDescription) throws MendelException {
        String[] slashSegments = rawDescription.split(" /by ");
        String[] misplacedSegments = rawDescription.split("/by");
        String[] mainMessage = slashSegments[0].split(" ");
        ConditionalExceptionHandler.of()
                .conditionTriggerException(mainMessage.length == 1 && slashSegments.length < 2,
                        "OOPS! deadline needs more details.\nAdd description and due.")
                .conditionTriggerException(misplacedSegments.length != slashSegments.length,
                        "OOPS! deadline due wrongly formatted\nPlease add spaces around /by")
                .conditionTriggerException(mainMessage.length == 1,
                        "OOPS! deadline description cannot be empty.\nAdd description.")
                .conditionTriggerException(slashSegments.length < 2,
                        "OOPS! deadline due cannot be empty.\nPlease indicate a due.")
                .conditionTriggerException(slashSegments.length > 2,
                        "OOPS! I am unsure of due.\nPlease specify only one due.");
        String endMsg = slashSegments[1];
        ConditionalExceptionHandler.of()
                .conditionTriggerException(endMsg.isEmpty(), "OOPS! I am unsure of due.\nPlease specify a due.");
    }

    /**
     * Checks if the given date matches the due date of this Deadline task.
     *
     * @param formattedDate The date to be compared in a formatted string.
     * @return true if the formattedDate matches the task's due date, ignoring the time component; false otherwise.
     */
    @Override
    public boolean isTargetDueDate(String formattedDate) {
        return new DateTimeManager(formattedDate).removeTimeStamp()
                .equals(new DateTimeManager(this.by).removeTimeStamp());
    }

    @Override
    public boolean isIncompleteWithinTargetDueDate(String formattedDate) {
        DateTimeManager inputDate = new DateTimeManager(formattedDate);
        DateTimeManager toDate = new DateTimeManager(this.by);
        if (!inputDate.isValidFormat()) {
            throw new MendelException("Invalid due date. Write in form Month Day Year such as Aug 09 2024");
        }
        if (!toDate.isValidFormat()) {
            return false;
        }
        long timeDeadline = inputDate.toEpochTime();
        long timeTo = new DateTimeManager(toDate.removeTimeStamp()).toEpochTime();
        boolean isTaskInRange = timeDeadline > timeTo;
        return isTaskInRange && !super.getStatus();
    }

    /**
     * Parses the details of this Deadline task into a string format suitable for storage in database.
     *
     * @return A string containing the task type, status, description, and due date.
     */
    @Override
    public String parseDetailsForDB() {
        return String.format("D | %d | %s | %s", super.getStatus() ? 1 : 0, this.description, this.by);
    }

    /**
     * Returns a string representation of the Deadline task, including its type and status.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public boolean isMatchingDescription(String matchingString) {
        return this.description.contains(matchingString);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
