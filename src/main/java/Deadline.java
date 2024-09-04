import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    LocalDate deadline;
    static DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = super.stringToDate(deadline);
    }

    Deadline(int status, String task, String deadline) {
        super(status, task);
        this.deadline = super.stringToDate(deadline);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DATEFORMAT) + ")";
    }

    String toSaveAsString() {
        return "D" + super.toSaveAsString() + "/" + this.deadline.toString();
    }
}