/**
 * This class is a subclass of ToothlessExceptions.
 */
public class NoTimeline extends ToothlessExceptions{

    /**
     * Constructor for NoTimeline.
     * @param taskType the type of task that the user is trying to create
     * @param instruction the format for the task type
     */
    public NoTimeline(String taskType, String instruction) {
        super("The timing for " + taskType +  " is not specified! :o\n" +
                "Please enter a valid timeline.\n\n" +
                "The format for " + taskType + " is: " + instruction + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }
}
