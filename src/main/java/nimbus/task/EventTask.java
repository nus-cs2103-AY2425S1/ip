package nimbus.task;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.DateTime;

public class EventTask extends Task {
    private final String taskName;
    private final DateTime startTime;
    private final DateTime endTime;

    public EventTask(String taskName, String startTime, String endTime)
            throws WrongDateTimeFormatException {
        super(taskName);
        this.taskName = taskName;
        this.startTime = new DateTime(startTime);
        this.endTime = new DateTime(startTime, endTime);
    }

    public EventTask(String taskName, boolean isCompleted, String startTime, String endTime)
            throws WrongDateTimeFormatException {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.startTime = new DateTime(startTime);
        this.endTime = new DateTime(startTime, endTime);
    }

    public DateTime getEventDate() {
        return startTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.isCompleted() ? "1" : "0") + " | " + taskName + " | "
                + startTime.toStorageFormat() + " - " + endTime.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
