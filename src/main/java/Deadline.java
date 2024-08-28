import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    public LocalDate getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = LocalDate.parse(by);
    }

    public Deadline(boolean done, String task, String by) {
        super(done, task);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {

        String task = super.getTask() + " "
                + String.format("(by: %s)", by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return super.getDone() ? "[D][X] " + task : "[D][ ] " + task;
    }

}
