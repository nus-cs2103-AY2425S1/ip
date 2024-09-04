import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class Task {
    String task;
    boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    Task(int status, String task) {
        this.task = task;
        this.isDone = (status == 1);
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    String getDescription() {
        return this.task;
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    String toSaveAsString() {
        if (this.isDone) {
            return "1/" + this.task;
        } else {
            return "0/" + this.task;
        }
    }

    LocalDate stringToDate(String dateString) {
        // assume dateString is in valid form yyyy-mm-dd
        dateString = dateString.strip();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, dateFormat);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}