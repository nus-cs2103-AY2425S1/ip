package bimo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String details, LocalDate dueDate) {
        super(details);
        this.dueDate = dueDate;
    }

    /**
     * Format end date to write into file
     * @return Returns start and end time as text
     */
    public String getDateAsText() {
        return "|" + this.dueDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
