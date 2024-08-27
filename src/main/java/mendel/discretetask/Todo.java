package mendel.discretetask;

import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

/**
 * Represents a Todo task. The Todo class extends the Task class
 * ad includes an attribute to handle the description.
 */
public class Todo extends Task {
    private final String description;

    /**
     * Constructs a Todo object with description.
     *
     * @param description The description of the Todo task.
     */
    private Todo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Factory method to create a new Todo object by parsing
     * raw input for task description and the due date.
     *
     * @param rawDescription The raw description string containing the task description.
     * @return A new Todo object with the parsed description.
     */
    public static Todo of(String rawDescription) {
        String[] descriptionLst = parseDescription(rawDescription);
        return new Todo(descriptionLst[0]);
    }

    /**
     * Factory method to load a Todo object from stored data.
     *
     * @param mark A boolean indicating whether the database task was marked as done.
     * @param description The database description of the task.
     * @return A Todo object initialized with the provided parameters.
     */
    public static Todo loadOf(boolean mark, String description) {
        Todo initObj = new Todo(description);
        if (mark) {
            initObj.markAsDone();
        } else {
            initObj.markAsUnDone();
        }
        return initObj;
    }

    /**
     * Parses the raw description string to extract the task description.
     *
     * @param rawDescription The raw description string containing the task description.
     * @return A String array containing the parsed description.
     * @throws MendelException if there are issues with the format or content of the raw description.
     */
    private static String[] parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] segments = rawDescription.split(" ");
        String reformattedMsg = "";
        for (int i = 1; i < segments.length; i++) {
            if (i == segments.length - 1) {
                reformattedMsg += segments[i];
            } else {
                reformattedMsg += segments[i] + " ";
            }
        }
        return new String[]{reformattedMsg};
    }

    /**
     * Validates the raw description string and handles errors such as missing description.
     *
     * @param rawDescription The raw description string to be validated.
     * @throws MendelException if the description is empty.
     */
    private static void handleError(String rawDescription) throws MendelException {
        String[] segments = rawDescription.split(" ");
        ConditionalExceptionHandler.of()
                .conditionTriggerException(segments.length == 1,
                        "OOPS! todo description cannot be empty.\nAdd description.");
    }

    /**
     * Checks if the given date matches the due date for this Todo task.
     * Since a Todo task does not have a due date, this method always returns false.
     *
     * @param formattedDate The date to be compared in a formatted string.
     * @return false as a Todo task does not have a due date.
     */
    @Override
    public boolean isTargetDueDate(String formattedDate) {
        return false;
    }

    /**
     * Parses the details of this Todo task into a string format suitable for storage in database.
     *
     * @return A string containing the task type, status, and description.
     */
    @Override
    public boolean isMatchingDescription(String matchingString) {
        return this.description.contains(matchingString);
    }

    @Override
    public String parseDetailsForDB() {
        return String.format("T | %d | %s", super.getStatus() ? 1 : 0, this.description);
    }

    /**
     * Returns a string representation of the Todo task, including its type and status.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
