import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private final LocalDate dueDate;

    Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    Deadline(String name, LocalDate dueDate, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.dueDate.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    String toSaveState() {
        return "D" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/"
                + this.dueDate.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd")) + "\n";
    }
}
