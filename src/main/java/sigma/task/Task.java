package sigma.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task.
 */
public class Task {
    private int id;
    private boolean status;
    private String desc;
    private static int count = 1;

    /**
     * Creates a task with the given description.
     * @param desc Description of the task.
     */
    public Task(String desc) {
        this.id = count;
        this.status = false;
        this.desc = desc;
        count++;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * Returns the status of the task.
     * @return String representation of the status of the task.
     */
    public String getStatusString() {
        return status ? "X" : " ";
    }

    public String getTaskType() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), getDesc());
    }
}
