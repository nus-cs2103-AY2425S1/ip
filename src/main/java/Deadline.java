import java.time.LocalDate;

class Deadline extends Task {
    LocalDate deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = super.stringToDate(deadline);
    }

    Deadline(int status, String task, String deadline) {
        super(status, task);
        this.deadline = super.stringToDate(deadline);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }

    String toSaveAsString() {
        return "D" + super.toSaveAsString() + "/" + this.deadline.toString();
    }
}