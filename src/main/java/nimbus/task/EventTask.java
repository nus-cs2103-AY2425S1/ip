package nimbus.task;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.DateTime;

/**
 * Tasks that has a start and end time
 */
public class EventTask extends Task {
    private final String taskName;
    private final DateTime startTime;
    private final DateTime endTime;


    /**
     * Creates an EventTask object with its taskName, start time and end time with
     * default incomplete status
     *
     * @param taskName
     * @param startTime
     * @param endTime
     * @throws WrongDateTimeFormatException if date time format for start and end times are wrong
     */
    public EventTask(String taskName, String startTime, String endTime)
            throws WrongDateTimeFormatException {
        super(taskName);
        this.taskName = taskName;
        this.startTime = new DateTime(startTime);
        this.endTime = new DateTime(startTime, endTime);
    }

    /**
     * Creates an EventTask object with its taskName, start time and end time with
     * default completed status
     *
     * @param taskName
     * @param startTime
     * @param endTime
     * @throws WrongDateTimeFormatException if date time format for start and end times are wrong
     */
    public EventTask(String taskName, boolean isCompleted, String startTime, String endTime)
            throws WrongDateTimeFormatException {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.startTime = new DateTime(startTime);
        this.endTime = new DateTime(startTime, endTime);
    }

    /**
     * gets event start date
     *
     * @return DateTime value of startTime
     */
    public DateTime getEventDate() {
        return startTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.isComplete() ? "1" : "0") + " | " + taskName + " | "
                + startTime.toStorageFormat() + " - " + endTime.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
