import java.util.Date;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    private String getDeadlineString() {
        return  " (by: " + deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDeadlineString();
    }
}
