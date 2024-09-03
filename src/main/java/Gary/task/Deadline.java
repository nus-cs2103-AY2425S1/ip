package Gary.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String dueDate;
    private LocalDate deadline;

    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDate(dueDate);
    }
    private LocalDate parseDate(String dateTime) {
        return LocalDate.parse(dateTime, inputFormatter);
    }
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.deadline.format(inputFormatter);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return super.equals(o) &&
                (dueDate == null ? deadline.dueDate == null : this.dueDate.equals(deadline.dueDate));
    }
}
