import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;
    public Deadline(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDate deadline) {
        this(name, deadline, false);
    }

    @Override
    public String toString() {
        String str = String.format("[D]%s (by: %s)", super.toString(),
                DateTimeFormatter.ofPattern("LLL dd yyyy").format(this.deadline));
        return str;
    }

    @Override
    public String encode() {
        return "D|" + super.encode() + "|" + this.deadline;
    }
}
