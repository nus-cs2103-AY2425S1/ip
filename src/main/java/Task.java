package ip.derrick ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Change the task status to DONE.
     */
    public void setMark() {
        this.isDone = true;
    }

    /**
     * Change the task status to UNDONE.
     */
    public void setUnmark() {
        this.isDone = true;
    }
    
    /**
     * Returns an 'X' if the task is marked as DONE, otherwise returns " ".
     *
     * @return A string indicating the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String changeFormat() {
        return this.description;
    }

    public LocalDate convertDate(String date) {
        return null;
    }
}