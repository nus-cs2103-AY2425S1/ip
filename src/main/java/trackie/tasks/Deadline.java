package trackie.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private String type = "D";
    private LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String description, String deadline, int status) {
        super(description, status);
        this.deadline = LocalDate.parse(deadline);
    }

    public String getTaskInfo() {
        return(String.format("%s (by: %s)", super.description, this.deadline));
    }

    public String getTaskType() {
        return(this.type);
    }

    public String getDeadline() {
        return this.deadline.toString();
    }
}
