package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSpecificDeadline extends Deadline {
    private LocalTime dueTime;

    public TimeSpecificDeadline(String title, LocalDate dueDate, LocalTime dueTime) {
        super(title, dueDate);
        this.dueTime = dueTime;
    }

    @Override
    public String toData() {
        return super.toData() + "/at " + dueTime;
    }

    @Override
    public String toString() {
        return super.toString() + " at " + dueTime;
    }
}
