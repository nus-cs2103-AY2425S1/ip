import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }

    public String getDates() {
        return "";
    }
    protected String dateConverter(LocalDate d) {
        return d.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) +" "+ d.getDayOfMonth() + " " + d.getYear();
    }
    public String toString() {
        return getStatusIcon()+description;
    }

}
