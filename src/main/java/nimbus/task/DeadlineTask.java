package nimbus.task;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.DateTime;

/**
 * Tasks that has a deadline
 */
public class DeadlineTask extends Task {
    private String taskName;
    private DateTime deadline;

    /**
     * Creates DeadlineTasks with default incomplete status
     *
     * @param taskName
     * @param deadline
     * @throws WrongDateTimeFormatException
     */
    public DeadlineTask(String taskName, String deadline) throws WrongDateTimeFormatException {
        super(taskName);
        this.taskName = taskName;
        this.deadline = new DateTime(deadline);
    }

    /**
     * Creates DeadlineTasks with default completed status
     *
     * @param taskName
     * @param deadline
     * @throws WrongDateTimeFormatException
     */
    public DeadlineTask(String taskName, boolean isCompleted, String deadline) throws WrongDateTimeFormatException {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.deadline = new DateTime(deadline);
    }

    /**
     * Gets the deadline of the task
     *
     * @return a DateTime object that is the deadline of the task
     */
    public DateTime getDeadline() {
        return deadline;
    }



    @Override
    public String toFileFormat() {
        return "D | " + (super.isComplete() ? "1" : "0") + " | "
                + this.taskName + " | " + deadline.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
