package seedu.maxine.task;

public class Deadline extends Task {

    protected String deadline;

    public Deadline() {
        super();
    }

    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = dateTimeParser(deadline.trim());
        } catch (Exception e) {
            this.deadline = deadline;
        }
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + deadline + ")";
    }

    @Override
    public String writeToFile() {
        return "D" + super.writeToFile() + " / " + deadline;
    }

}

