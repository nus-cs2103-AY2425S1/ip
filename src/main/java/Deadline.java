import java.util.ArrayList;

/**
 * This class implements a Deadline task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Deadline extends Task {

    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        String str = "[D]" + super.toString() + String.format("( by: %s)", this.deadline);
        return str;
    }
}
