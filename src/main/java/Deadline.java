import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDateTime deadline) {
        this(name, deadline, false);
    }

    @Override
    public String toString() {
        String str = String.format("[D]%s (by: %s)", super.toString(),
                DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm").format(this.deadline));
        return str;
    }

    @Override
    public String encode() {
        return "D|" + super.encode() + "|" + this.deadline;
    }
}
