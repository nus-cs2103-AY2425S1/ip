import java.time.LocalDateTime;

public class Deadline extends Task {

    public LocalDateTime endTime;

    public Deadline(String name, LocalDateTime endTime) {
        super(name);
        this.endTime = endTime;
    }

    public Deadline(String name, LocalDateTime endTime, boolean done) {
        super(name, done);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", endTime);
    }

    public String toFileString() {
        return String.format("D\n%s%s\n", super.toFileString(), endTime);
    }
}
