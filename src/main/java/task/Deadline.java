package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Deadline extends Task {

    private LocalDateTime timedeadline;
    private LocalDate datedeadline;
    
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.timedeadline = deadline;
    }

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.datedeadline = deadline;
    }

    private String getDisplayStringDeadline() {
        if (this.timedeadline != null) {
            return this.timedeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else {
            return this.datedeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    private String getStringDeadline() {
        if (this.timedeadline != null) {
            return this.timedeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return this.datedeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }


    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.timedeadline = deadline;
    }

    public Deadline(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.datedeadline = deadline;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringDeadline = this.getStringDeadline();
        return "D," + done + "," + this.getName() + "," + stringDeadline;
    }

    @Override
    public String toString() {
        String stringDeadline = this.getDisplayStringDeadline();
        return "[D] " + super.toString() + " (by: " + stringDeadline + ")";
    }

}
