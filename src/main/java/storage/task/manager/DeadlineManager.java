package storage.task.manager;

import exceptions.BrockException;
import task.Deadline;
import task.Task;
import utility.StorageUtility;

/**
 * Class to manage deadline strings in task storage.
 */
public class DeadlineManager extends TaskManager {
    private String[] processDeadlineBody(String deadlineBody) throws BrockException {
        String[] parts = deadlineBody.split("\\(by: ", 2);
        if (parts.length < 2) {
            throw new BrockException("Invalid deadline entry - missing due date!");
        }
        return parts;
    }

    /**
     * Validates the due dateTime of the deadline string.
     *
     * @param dateTime dateTime string to be verified.
     * @return The due date and due time, separated out, if valid.
     * @throws BrockException If dateTime is invalid.
     */
    private String[] processDueDateTime(String dateTime) throws BrockException {

        String[] dateTimeParts = dateTime.split(", ");

        String dueDateString = dateTimeParts.length == 1
                ? StorageUtility.parseDate(StorageUtility.removeCloseBracket(dateTimeParts[0]))
                : StorageUtility.parseDate(dateTimeParts[0]);
        String dueTimeString = dateTimeParts.length == 1
                ? ""
                : StorageUtility.removeCloseBracket(dateTimeParts[1])
                .replace(":", "");

        return new String[]{dueDateString, dueTimeString};
    }

    /**
     * Creates a {@code Deadline} object corresponding to a deadline string.
     *
     * @param deadlineBody   String storing deadline description and due datetime.
     * @param deadlineStatus Character representing deadline status.
     * @return {@code Deadline} object created.
     * @throws BrockException If date and time are invalid when constructing object.
     *                        (They should already be validated)
     */
    @Override
    public Task convertToTaskObject(String deadlineBody, char deadlineStatus) throws BrockException {
        String[] parts = this.processDeadlineBody(deadlineBody);

        String description = parts[0];
        String dateTime = parts[1];
        String[] dueValues = this.processDueDateTime(dateTime);

        Task deadlineTask;
        if (dueValues[1].isEmpty()) {
            // Due time is empty
            // Create deadline with no time
            deadlineTask = new Deadline(description, dueValues[0]);
        } else {
            // Due time is not empty
            // Create deadline with time
            deadlineTask = new Deadline(description, dueValues[0], dueValues[1]);
        }

        assert deadlineStatus == 'X' | deadlineStatus == ' ' : "Invalid deadline status extracted.";
        if (deadlineStatus == 'X') {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }
}
