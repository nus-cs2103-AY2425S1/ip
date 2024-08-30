package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime date;

    public Deadline(String action, LocalDateTime date) {
        super(action);
        this.date = date;
    }

    public Deadline(String action, boolean complete, LocalDateTime date) {
        super(action, complete);
        this.date = date;
    }

    @Override
    public LocalDate getDate() {
        return this.date.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "D | " + super.toString() + " | " + date.format(formatter);
    }
}

