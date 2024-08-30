package lutodo.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return  The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "_"); // mark done task with X
    }

    public static LocalDate manageDate(String dateMessage) {
        return LocalDate.parse(dateMessage,DateTimeFormatter.ISO_DATE);
    }

    public static LocalTime manageTime(String timeMessage) {
        return LocalTime.parse(timeMessage,DateTimeFormatter.ISO_TIME);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
