package toothless.exceptions;

/**
 * This class is a subclass of ToothlessExceptions.
 * It is used to handle the case when the user does not provide a description for the task.
 */
public class NoDescriptionExceptions extends ToothlessExceptions {

    /**
     * Constructor for NoDescription.
     *
     * @param taskType    the type of task that the user is trying to create
     * @param instruction the format for the task type
     */
    public NoDescriptionExceptions(String taskType, String instruction) {
        super("You need to have decription for your " + taskType + " task!\n"
                + "Please enter a valid description.\n\n"
                + "The format for " + taskType + " is: " + instruction + "\n");
    }
}
