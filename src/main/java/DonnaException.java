public class DonnaException extends Exception{
    public DonnaException(String message) {
        super(message);
    }

    public static DonnaException emptyDescription(String taskType) {
        return new DonnaException("Please provide the description for a " + taskType + " task");
    }

    public static DonnaException emptyDeadline() {
        return new DonnaException("Please provide a deadline for this task!" + "\n" + "Use /by to provide a deadline.");
    }

    public static DonnaException emptyEventTime() {
        return new DonnaException("Please provide the timings for this event!"
                + "\n" + "Use /from and /to to provide timings.");
    }

    public static DonnaException invalidTaskType(String taskType) {
        return new DonnaException("Invalid request: " + taskType + "!"
                + "\n" + "Please use a valid task type (todo / deadline / event);" + "\n" +
                "type list to view the list of tasks, " + "\n" +
                "or type delete to remove tasks from the list." + "\n"
                + "Use /by (for a deadline) and /from and /to (for an event) to specify" + "\n" +
                "timings for the task.");
    }

    public static DonnaException invalidTaskNumber() {
        return new DonnaException("Invalid task number :(" + "\n"
                + "No task assigned to this number yet. Retry with a valid task number!");
    }
}