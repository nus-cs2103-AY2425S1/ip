/**
 * This exception is thrown when a task is created without the necessary date or time information.
 * <p>
 * The {@code MissingDateException} is a custom exception that extends the {@code Exception} class.
 * It is used to indicate that a deadline or event task is missing required date or time details.
 * </p>
 */
public class MissingDateException extends Exception {
    private String taskType;

    public MissingDateException(String taskType) {
        this.taskType = taskType;
    }
    @Override
    public String toString() {
        switch (taskType) {
            case "deadline":
                return "OOPS!!! Deadlines needs to be followed by a date in the format: " +
                        "'submit report /by 11/10/2019 5pm'.";
            case "event":
                return "OOPS!!! Events needs to be followed by both a start and end date/time in the format: " +
                        "'team project meeting /from 2/10/2019 2pm /to 4pm'.";
            default:
                return "OOPS!!! The date/time information is missing or incomplete.";
        }
    }

}
