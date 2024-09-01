package astor.task;

/**
 * Represents a generic task with a description and status.
 *
 * This class provides functionality to mark a task as done or undone, check its status,
 * and retrieve its description.
 */
public class Task {
    private boolean status;
    private String taskInfo;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param taskInfo a description of the task
     */
    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.status = false;
    }


    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    /**
     * Returns a string representation of the task.
     *
     * The string representation includes the task status and description. The status is shown
     * as "[X]" if the task is done, and "[ ]" if it is not.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String s = "";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.taskInfo;
        return s;
    }

    /**
     * Parses a deadline string and converts it to ISO-8601 format.
     *
     * This method converts a deadline string from the format "dd/MM/yyyy HHmm" to ISO-8601 format.
     * If the time part is not provided, it defaults to "00:00:00".
     *
     * @param deadline a string representing the deadline in "dd/MM/yyyy HHmm" format
     * @return the deadline in ISO-8601 format (yyyy-MM-ddTHH:mm:ss)
     */
    public static String generateParse(String deadline) {
        String[] dateAndTime = deadline.trim().split("\\s+|/");
        String parsedDate = dateAndTime[2] + "-" + dateAndTime[1] + "-" + dateAndTime[0];

        if (dateAndTime.length > 3) {
            String hour = dateAndTime[3].substring(0,2);
            String minute = dateAndTime[3].substring(2);
            parsedDate +=  "T" + hour + ":" + minute;
        } else {
            parsedDate += "T00:00:00";
        }
        return parsedDate;
    }

    /**
     * Returns a string description of the task data for saving or processing.
     *
     * This method provides a default implementation that returns an empty string.
     * Subclasses should override this method to provide specific task data descriptions.
     *
     * @return a string description of the task data
     */
    public String dataDescription() {
        return "";
    }
}
