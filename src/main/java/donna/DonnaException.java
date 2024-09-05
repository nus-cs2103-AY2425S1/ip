package donna;

public class DonnaException extends Exception{
    public DonnaException(String message) {
        super(message);
    }

    public static DonnaException emptyDescription(String taskType) {
        return new DonnaException("Please provide the description for a " + taskType + " task");
    }

    public static DonnaException emptyDeadline() {
        return new DonnaException("Please provide a deadline for this task!" + "\n"
                + "Use /by to provide a deadline.");
    }

    public static DonnaException emptyEventTime() {
        return new DonnaException("Please provide the timings for this event!"
                + "\n" + "Use /from and /to to provide timings.");
    }

    public static DonnaException invalidTaskType(String taskType) {
        return new DonnaException("I'm sorry I don't understand that :(\n"
                + "Please use a valid task type \n"
                + "(todo / deadline / event)\n"
                + "or you may type list , delete or mark / unmark\n"
                + "to view, delete or mark tasks");
    }

    public static DonnaException invalidTaskNumber() {
        return new DonnaException("No task assigned to this number yet.\n"
                + "Retry with a valid task number!");
    }
}