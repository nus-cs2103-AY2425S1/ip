package Bunbun.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class implements a Deadline task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String genFileString() {
        String isComplete = (this.isComplete()) ? "true" : "false";
        String taskDescription = String.format("%s;%s;%s;%s\n", "deadline", isComplete, this.getTask(),
                this.deadline);
        return taskDescription;
    }

    @Override
    public String toString() {
        String str = "[D]" + super.toString() + String.format("( by: %s )", this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return str;
    }
}
