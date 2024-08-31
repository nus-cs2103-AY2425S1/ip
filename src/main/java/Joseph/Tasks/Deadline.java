package Joseph.Tasks;

import Joseph.Tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime due;

    public Deadline(String desc, String due) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.due = LocalDateTime.parse(due, formatter);
    }

    public LocalDateTime getDue() {
        return this.due;
    }

    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.getDesc() + " due: " + this.due.format(formatter);
    }
}
