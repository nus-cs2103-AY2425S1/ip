package wansbot.tasks;

/**
 * Exception when specified number does not lie in the range of the userTaskList.
 */
public class NotANumMarkingException extends RuntimeException {

    /**
     * Prints to console when task number specified by user does not exist.
     */
    public NotANumMarkingException(int taskListNumber) {
        super(taskListNumber + " is not a numbered task in the taskList!");
    }
}
