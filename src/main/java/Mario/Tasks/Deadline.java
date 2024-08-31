package Mario.Tasks;

public class Deadline extends Task {
    private final String deadline;
    private String type;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.type = "D";
    }

    public Deadline(String name, String deadline, boolean isCompleted) {
        super(name, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        String status;
        if (isCompleted) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[D]" + status + " " + name + " (by: " + this.deadline + ")";
    }


    @Override
    public String toFileFormat() {
        int status = this.isCompleted ? 1 : 0;
        return "D | " + status + " | " + this.name + " | " + this.deadline;
    }

}
