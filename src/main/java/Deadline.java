import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDate;
    public Deadline(String task, LocalDateTime dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }

    @Override
    public String saveAsCSV() {
        return "D," + super.saveAsCSV() + "," + this.dueDate;
    }
}
