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


    public void markStatus() {
        this.isDone = true;
    }

    public void unmarkStatus() {
        this.isDone = false;
    }

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