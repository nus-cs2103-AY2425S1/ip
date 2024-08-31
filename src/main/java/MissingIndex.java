/**
 * MissingIndex class is a subclass of ToothlessExceptions.
 * It is used to handle the case when the user does not provide an index for the task.
 */
public class MissingIndex extends ToothlessExceptions{

    /**
     * Constructor for MissingIndex.
     * @param taskType the type of task that the user is trying to create
     * @param instruction the format for the task type
     */
    public MissingIndex(String taskType, String instruction) {
        super("The index for " + taskType + " is missing! :o" +
                "Please enter a valid index.\n\n" +
                "The format for " + taskType + " is: " + instruction + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }
}
