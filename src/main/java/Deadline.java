public class Deadline extends Task {
    public String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.deadline);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.description, false, this.deadline);
    }

    public String getType() {
        return "D";
    }

    public String getTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", super.toString(), this.deadline);
    }

}
