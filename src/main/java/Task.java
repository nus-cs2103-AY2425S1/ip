import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean completed;
    private String taskType;

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String checkMark() {
        if (completed) {
            return "X";
        } else {
            return " ";
        }
    }

    public String completeStatus() {
        if (completed) {
            return "1";
        } else {
            return "0";
        }
    }

    public String processDate(String date) throws DateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeException d) {
            return date;
        }
    }

    public void check() {
        this.completed = true;
    }

    public void uncheck() {
        this.completed = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public abstract String toString();

    public abstract String toTask();
}
