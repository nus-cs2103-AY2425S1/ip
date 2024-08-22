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
        return new DonnaException("Invalid task type: " + taskType + "!"
                + "\n" + "Please use a valid task type (todo / deadline / event)." + "\n"
                + "Use /by (for a deadline) and /from and /to (for an event) to specify timings for the task.");
    }
}