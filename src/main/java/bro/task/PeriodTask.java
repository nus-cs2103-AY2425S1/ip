package bro.task;

/**
 * Represents anPeriod task tracked by Bro. A <code>Period</code> object
 * is a type of task that has a specific start and end date associated with it.
 */
public class PeriodTask extends Task {
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a new PeriodTask with the specified content, start date, and end date.
     * The task is initially marked as not completed.
     *
     * @param content   The content of the Period task.
     * @param startDate The start date of the Period task.
     * @param endDate   The end date of the Period task.
     */
    public PeriodTask(String content, String startDate, String endDate) {
        super(content);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a new PeriodTask with the specified content, start date, end date,
     * and completion status.
     *
     * @param content     The content of the Period task.
     * @param startDate   The start date of the Period task.
     * @param endDate     The end date of the Period task.
     * @param isCompleted The initial completion status of the task.
     */
    public PeriodTask(String content, String startDate, String endDate, boolean isCompleted) {
        super(content, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of the Period task, indicating it is an Period task
     * along with its content, completion status, start date, and end date.
     *
     * @return A string representation of the Period task, prefixed with "[P]"
     *         and showing its start and end dates.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + "(from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
