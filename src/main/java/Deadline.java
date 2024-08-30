import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private LocalDateTime time;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toListString() {
        return "D" + super.toListString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) +")";
    }
}
